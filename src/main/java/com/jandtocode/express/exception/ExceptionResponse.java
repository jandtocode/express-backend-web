package com.jandtocode.express.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de error. Se devuelve en todos los casos 400, 401, 403, 404, 409 y 500.")
public class ExceptionResponse {

    @Schema(description = "Código HTTP del error. Coincide con el status de la respuesta.",
            example = "401")
    private int status;

    @Schema(description = "Mensaje que explica el error.",
            example = "Identification or password is incorrect")
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
