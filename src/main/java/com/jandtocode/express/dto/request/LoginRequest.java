package com.jandtocode.express.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales para iniciar sesión.")
public class LoginRequest {

    @Schema(description = "Identificación con la que se registró el usuario.",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String identification;

    @Schema(description = "Contraseña del usuario.",
            example = "Clave123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public LoginRequest() {}

    public LoginRequest(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}