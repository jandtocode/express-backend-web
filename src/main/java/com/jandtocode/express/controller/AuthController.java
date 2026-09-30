package com.jandtocode.express.controller;

import com.jandtocode.express.dto.request.LoginRequest;
import com.jandtocode.express.dto.request.RegisterUserRequest;
import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.dto.response.RegisterUserResponse;
import com.jandtocode.express.exception.ExceptionResponse;
import com.jandtocode.express.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "1. Autenticación", description = "Registro de usuarios e inicio de sesión")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "Iniciar sesión",
            description = """
                    Valida las credenciales y crea la sesión del usuario (cookie `JSESSIONID`).
                    Es el **primer paso de los caminos 2, 3 y 4**, y el segundo del camino 1.

                    Si se falla la contraseña 3 veces seguidas, el usuario queda **bloqueado**.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso. Se crea la sesión.",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Falta la identificación o la contraseña.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "Identificación o contraseña incorrecta.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuario bloqueado por 3 intentos fallidos.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest,
                                               @Parameter(hidden = true) HttpSession session) {
        LoginResponse response = authService.login(
                loginRequest.getIdentification(),
                loginRequest.getPassword()
        );

        // Guardar userId en sesión
        session.setAttribute("userId", response.getUserId());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Registrar usuario nuevo",
            description = """
                    Crea un usuario y su dashboard por defecto. Es el **primer paso del camino 1**.

                    **Importante:** el registro NO inicia sesión. Después hay que ejecutar
                    `POST /api/auth/login`.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente.",
                    content = @Content(schema = @Schema(implementation = RegisterUserResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Falta algún campo obligatorio o las contraseñas no coinciden.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409",
                    description = "La identificación ya está registrada.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = authService.register(
                request.getName(),
                request.getLastName(),
                request.getIdentification(),
                request.getPassword(),
                request.getConfirmPassword()
        );
        return ResponseEntity.ok(response);
    }

}