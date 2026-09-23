package com.jandtocode.express.service.impl;

import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.dto.response.RegisterUserResponse;
import com.jandtocode.express.entity.User;
import com.jandtocode.express.repository.RegisterUserRepository;
import com.jandtocode.express.repository.UserRepository;
import com.jandtocode.express.service.AuthService;
import com.jandtocode.express.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserRepository registerUserRepository;

    private static final Integer MAX_FAILED_ATTEMPTS = 3;

    @Override
    public LoginResponse login(String identification, String password) {

        // Validar campos vacíos
        if (identification == null || identification.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.IDENTIFICATION_REQUIRED);
        }

        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.PASSWORD_REQUIRED);
        }

        // Buscar usuario
        Map<String, String> credentials = userRepository.findCredentialsByIdentification(identification);

        // Usuario no existe
        if (credentials == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UserUtils.INVALID_CREDENTIALS);
        }

        // Extrae datos
        Integer userId = Integer.parseInt(credentials.get("id"));
        String storedPassword = credentials.get("password");
        boolean isBlocked = Boolean.parseBoolean(credentials.get("isBlocked"));
        Integer failedAttempts = Integer.parseInt(credentials.get("failedAttempts"));

        // Validar si está bloqueado
        if (isBlocked) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, UserUtils.USER_BLOCKED);
        }

        // Validar contraseña
        if (!storedPassword.equals(password)) {
            failedAttempts++;
            userRepository.updateFailedAttempts(userId, failedAttempts);

            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                userRepository.blockUser(userId);
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, UserUtils.USER_BLOCKED);
            }

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UserUtils.INVALID_CREDENTIALS);
        }

        // Login exitoso
        userRepository.resetFailedAttempts(userId);
        return new LoginResponse(
                true,
                UserUtils.SUCCESS,
                credentials.get("name"),
                credentials.get("lastName"),
                userId
        );
    }

    @Override
    public RegisterUserResponse register(String name, String lastName, String identification,
                                         String password, String confirmPassword) {

        // Validar que nombre no esté vacío
        if (name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.NAME_REQUIRED);
        }

        // Validar que apellido no esté vacío
        if (lastName == null || lastName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.LASTNAME_REQUIRED);
        }

        // Validar que identificación no esté vacía
        if (identification == null || identification.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.IDENTIFICATION_REQUIRED);
        }

        // Validar que password no esté vacío
        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.PASSWORD_REQUIRED);
        }

        // Validar que confirmPassword no esté vacío
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.CONFIRM_PASSWORD_REQUIRED);
        }

        // Validar que la identificación no esté duplicada en BD
        if (registerUserRepository.existsByIdentification(identification)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, UserUtils.DUPLICATE_IDENTIFICATION);
        }

        // Validar que password y confirmPassword coincidan
        if (!password.equals(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UserUtils.CHECK_DUPLICATE_PASSWORD);
        }

        // Crear nuevo usuario
        User user = new User(name, lastName, identification, password);

        // Guardar en BD
        User savedUser = registerUserRepository.save(user);

        // Retornar respuesta exitosa
        return new RegisterUserResponse(
                true,
                UserUtils.SUCCESS,
                savedUser.getIdentification()
        );
    }
}