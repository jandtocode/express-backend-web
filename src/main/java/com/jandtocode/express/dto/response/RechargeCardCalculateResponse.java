package com.jandtocode.express.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Resultado del cálculo de la recarga.")
public class RechargeCardCalculateResponse {

    @Schema(description = "Indica si el cálculo fue exitoso.", example = "true")
    private Boolean success;

    @Schema(description = "Mensaje del resultado.", example = "Cálculo de recarga exitoso")
    private String message;

    @Schema(description = "Id del usuario en sesión.", example = "1")
    private Integer userId;

    @Schema(description = "Tipo de pago enviado.", example = "Tarjeta")
    private String typePayment;

    @Schema(description = "Banco enviado.", example = "PiggyBank Pop")
    private String bank;

    @Schema(description = "Nombre del usuario registrado.", example = "Juan")
    private String name;

    @Schema(description = "Apellido del usuario registrado.", example = "Pérez")
    private String lastName;

    @Schema(description = "Saldo actual antes de la recarga.", example = "0.0")
    private double currentBalance;

    @Schema(description = "Recargas realizadas hasta ahora. 0 indica usuario nuevo.", example = "0")
    private int accumulatedRecharges;

    @Schema(description = "Fecha de la última recarga. 1900-01-01 indica que nunca ha recargado.",
            example = "1900-01-01")
    private LocalDate lastRechargeDate;

    @Schema(description = "Valor a recargar enviado.", example = "2000.0")
    private double valueRecharge;

    @Schema(description = "Indica si se aplicó bono.", example = "true")
    private boolean applyBonus;

    @Schema(description = "Valor del bono. 0 si no se aplicó.", example = "400.0")
    private double bonusValue;

    @Schema(description = "Valor de la recarga sin bono. Hoy devuelve el mismo valor que valueRecharge.",
            example = "2000.0")
    private double totalRecharge;

    @Schema(description = "Total final: valueRecharge + bonusValue.", example = "2400.0")
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
