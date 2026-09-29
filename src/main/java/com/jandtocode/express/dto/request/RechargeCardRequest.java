package com.jandtocode.express.dto.request;

public class RechargeCardRequest {
    private Long userId;
    private int totalTrips;
    private Double totalPayment;
    private Double currentBalance;

    public RechargeCardRequest(){}

    public RechargeCardRequest(Long userId, int totalTrips, Double totalPayment, Double currentBalance) {
        this.userId = userId;
        this.totalTrips = totalTrips;
        this.totalPayment = totalPayment;
        this.currentBalance = currentBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(int totalTrips) {
        this.totalTrips = totalTrips;
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
