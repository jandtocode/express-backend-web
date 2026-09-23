package com.jandtocode.express.dto.response;

public class RegisterUserResponse {

    private Boolean success;
    private String message;
    private String identification;

    public RegisterUserResponse() {
    }

    public RegisterUserResponse(Boolean success, String message, String identification) {
        this.success = success;
        this.message = message;
        this.identification = identification;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
