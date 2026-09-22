package com.jandtocode.express.dto.response;

public class LoginResponse {
    private Boolean success;
    private String message;
    private String name;
    private String lastName;

    public LoginResponse() {}

    public LoginResponse(Boolean success, String message, String name, String lastName) {
        this.success = success;
        this.message = message;
        this.name = name;
        this.lastName = lastName;
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
}