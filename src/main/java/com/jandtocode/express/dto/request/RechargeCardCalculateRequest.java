package com.jandtocode.express.dto.request;

public class RechargeCardCalculateRequest {
    private Long userId;
    private String typePayment;
    private String bank;
    private String name;
    private String lastName;
    private Double valueRecharge;

    public RechargeCardCalculateRequest () {}

    public RechargeCardCalculateRequest(Long userId, String typePayment, String bank, String name, String lastName, Double valueRecharge) {
        this.userId = userId;
        this.typePayment = typePayment;
        this.bank = bank;
        this.name = name;
        this.lastName = lastName;
        this.valueRecharge = valueRecharge;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public Double getValueRecharge() {
        return valueRecharge;
    }

    public void setValueRecharge(Double valueRecharge) {
        this.valueRecharge = valueRecharge;
    }
}
