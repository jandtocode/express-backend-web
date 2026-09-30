package com.jandtocode.express.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para registrar un usuario nuevo. Todos los campos son obligatorios.")
public class RegisterUserRequest {

    @Schema(description = "Nombre del usuario.",
            example = "Jandtocode",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Apellido del usuario.",
            example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "Identificación del usuario. Debe ser única: si ya existe, responde 409.",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String identification;

    @Schema(description = "Contraseña del usuario.",
            example = "Clave123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Confirmación de la contraseña. Debe ser igual a password, si no responde 400.",
            example = "Clave123",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String confirmPassword;

    public RegisterUserRequest() {
    }

    public RegisterUserRequest(String name, String lastName, String identification,
                               String password, String confirmPassword) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}