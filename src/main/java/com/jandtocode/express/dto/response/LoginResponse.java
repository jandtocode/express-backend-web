package com.jandtocode.express.dto.response;

public class LoginResponse {
    private Boolean success;
    private String message;
    private String name;
    private String lastName;
    private Integer userId;

    public LoginResponse() {}

    public LoginResponse(Boolean success, String message, String name, String lastName, Integer userId) {
        this.success = success;
        this.message = message;
        this.name = name;
        this.lastName = lastName;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}