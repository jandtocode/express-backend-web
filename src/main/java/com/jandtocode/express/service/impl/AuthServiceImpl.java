package com.jandtocode.express.service.impl;

import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.repository.UserRepository;
import com.jandtocode.express.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private static final Integer MAX_FAILED_ATTEMPTS = 3;

    @Override
    public LoginResponse login(String identification, String password) {

        // Primera validación: campos vacíos
        if (identification == null || identification.isEmpty() ||
                password == null || password.isEmpty()) {
            return new LoginResponse(false, "Identificación y contraseña son requeridas", null, null);
        }

        // Busca credenciales en BD
        Map<String, String> credentials = userRepository.findCredentialsByIdentification(identification);

        // Usuario no existe
        if (credentials == null) {
            return new LoginResponse(false, "Identificación o contraseña incorrecta", null, null);
        }

        // Extrae datos
        Integer userId = Integer.parseInt(credentials.get("id"));
        String storedPassword = credentials.get("password");
        Boolean isBlocked = Boolean.parseBoolean(credentials.get("isBlocked"));
        Integer failedAttempts = Integer.parseInt(credentials.get("failedAttempts"));

        // Usuario bloqueado
        if (isBlocked) {
            return new LoginResponse(false, "Usuario bloqueado. Contacte con el administrador", null, null);
        }

        // Contraseña incorrecta
        if (!storedPassword.equals(password)) {
            failedAttempts++;
            userRepository.updateFailedAttempts(userId, failedAttempts);

            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                userRepository.blockUser(userId);
                return new LoginResponse(false, "Usuario bloqueado. Contacte con el administrador", null, null);
            }

            return new LoginResponse(false, "Identificación o contraseña incorrecta", null, null);
        }

        // Login exitoso
        userRepository.resetFailedAttempts(userId);
        return new LoginResponse(
                true,
                "Login exitoso",
                credentials.get("name"),
                credentials.get("lastName")
        );
    }
}