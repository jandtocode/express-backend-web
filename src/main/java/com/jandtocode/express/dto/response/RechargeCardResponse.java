package com.jandtocode.express.dto.response;

public class RechargeCardResponse {
    private Boolean success;
    private String message;
    private Double currentBalance;
    private String lastRecharge;
    private Integer totalTrips;
    private boolean applyBonus;

    public RechargeCardResponse(){}

    public RechargeCardResponse(Boolean success, String message, Double currentBalance, String lastRecharge, Integer totalTrips, boolean applyBonus) {
        this.success = success;
        this.message = message;
        this.currentBalance = currentBalance;
        this.lastRecharge = lastRecharge;
        this.totalTrips = totalTrips;
        this.applyBonus = applyBonus;
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

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getLastRecharge() {
        return lastRecharge;
    }

    public void setLastRecharge(String lastRecharge) {
        this.lastRecharge = lastRecharge;
    }

    public Integer getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(Integer totalTrips) {
        this.totalTrips = totalTrips;
    }

    public boolean isApplyBonus() {
        return applyBonus;
    }

    public void setApplyBonus(boolean applyBonus) {
        this.applyBonus = applyBonus;
    }
}
