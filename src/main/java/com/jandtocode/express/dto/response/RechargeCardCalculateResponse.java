package com.jandtocode.express.dto.response;

import java.time.LocalDate;

public class RechargeCardCalculateResponse {

    private Boolean success;
    private String message;
    private Integer userId;
    private String typePayment;
    private String bank;
    private String name;
    private String lastName;
    private double currentBalance;
    private int accumulatedRecharges;
    private LocalDate lastRechargeDate;
    private double valueRecharge;
    private boolean applyBonus;
    private double bonusValue;
    private double totalRecharge;
    private double totalToPay;


    public RechargeCardCalculateResponse(){}

    public RechargeCardCalculateResponse(Boolean success, String message, Integer userId, String typePayment,
                                         String bank, String name, String lastName, double currentBalance,
                                         int accumulatedRecharges, LocalDate lastRechargeDate, double valueRecharge,
                                         double totalRecharge, double totalToPay, boolean applyBonus, double bonusValue) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.typePayment = typePayment;
        this.bank = bank;
        this.name = name;
        this.lastName = lastName;
        this.currentBalance = currentBalance;
        this.accumulatedRecharges = accumulatedRecharges;
        this.lastRechargeDate = lastRechargeDate;
        this.valueRecharge = valueRecharge;
        this.applyBonus = applyBonus;
        this.bonusValue = bonusValue;
        this.totalRecharge = totalRecharge;
        this.totalToPay = totalToPay;
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

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getAccumulatedRecharges() {
        return accumulatedRecharges;
    }

    public void setAccumulatedRecharges(int accumulatedRecharges) {
        this.accumulatedRecharges = accumulatedRecharges;
    }

    public LocalDate getLastRechargeDate() {
        return lastRechargeDate;
    }

    public void setLastRechargeDate(LocalDate lastRechargeDate) {
        this.lastRechargeDate = lastRechargeDate;
    }

    public double getValueRecharge() {
        return valueRecharge;
    }

    public void setValueRecharge(double valueRecharge) {
        this.valueRecharge = valueRecharge;
    }

    public boolean isApplyBonus() {
        return applyBonus;
    }

    public void setApplyBonus(boolean applyBonus) {
        this.applyBonus = applyBonus;
    }

    public double getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(double bonusValue) {
        this.bonusValue = bonusValue;
    }

    public double getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(double totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }
}
