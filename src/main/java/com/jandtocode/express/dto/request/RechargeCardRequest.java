package com.jandtocode.express.dto.request;

public class RechargeCardRequest {
    private Long userId;
    private int accumulateRecharges;
    private Double totalPayment;
    private Double currentBalance;

    public RechargeCardRequest(){}

    public RechargeCardRequest(Long userId, int accumulateRecharges, Double totalPayment, Double currentBalance) {
        this.userId = userId;
        this.accumulateRecharges = accumulateRecharges;
        this.totalPayment = totalPayment;
        this.currentBalance = currentBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAccumulateRecharges() {
        return accumulateRecharges;
    }

    public void setAccumulateRecharges(int accumulateRecharges) {
        this.accumulateRecharges = accumulateRecharges;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
