package com.jandtocode.express.service.impl;

import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.repository.UserRepository;
import com.jandtocode.express.service.AuthService;
import com.jandtocode.express.util.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private static final Integer MAX_FAILED_ATTEMPTS = 3;

    @Override
    public LoginResponse login(String identification, String password) {

        // Validar campos vacíos
        if (identification == null || identification.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LoginUtils.IDENTIFICATION_REQUIRED);
        }

        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, LoginUtils.PASSWORD_REQUIRED);
        }

        // Buscar usuario
        Map<String, String> credentials = userRepository.findCredentialsByIdentification(identification);

        // Usuario no existe
        if (credentials == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, LoginUtils.INVALID_CREDENTIALS);
        }

        // Extrae datos
        Integer userId = Integer.parseInt(credentials.get("id"));
        String storedPassword = credentials.get("password");
        boolean isBlocked = Boolean.parseBoolean(credentials.get("isBlocked"));
        Integer failedAttempts = Integer.parseInt(credentials.get("failedAttempts"));

        // Validar si está bloqueado
        if (isBlocked) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, LoginUtils.USER_BLOCKED);
        }

        // Validar contraseña
        if (!storedPassword.equals(password)) {
            failedAttempts++;
            userRepository.updateFailedAttempts(userId, failedAttempts);

            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                userRepository.blockUser(userId);
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, LoginUtils.USER_BLOCKED);
            }

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, LoginUtils.INVALID_CREDENTIALS);
        }

        // Login exitoso
        userRepository.resetFailedAttempts(userId);
        return new LoginResponse(
                true,
                LoginUtils.SUCCESS_LOGIN,
                credentials.get("name"),
                credentials.get("lastName"),
                userId
        );
    }
}