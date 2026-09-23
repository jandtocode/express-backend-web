package com.jandtocode.express.controller;

import com.jandtocode.express.dto.request.LoginRequest;
import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        LoginResponse response = authService.login(
                loginRequest.getIdentification(),
                loginRequest.getPassword()
        );

        // Guardar userId en sesión
        session.setAttribute("userId", response.getUserId());

        return ResponseEntity.ok(response);
    }

}
