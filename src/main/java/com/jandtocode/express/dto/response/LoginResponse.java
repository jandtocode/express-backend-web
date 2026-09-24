package com.jandtocode.express.dto.response;

public class LoginResponse {
    private Boolean success;
    private String message;
    private Integer userId;

    public LoginResponse() {}

    public LoginResponse(Boolean success, String message, Integer userId) {
        this.success = success;
        this.message = message;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}