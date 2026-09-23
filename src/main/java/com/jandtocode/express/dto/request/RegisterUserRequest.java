package com.jandtocode.express.dto.request;

public class RegisterUserRequest {

    private String name;
    private String lastName;
    private String identification;
    private String password;
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