package com.jandtocode.express.dto.response;

public class BalanceUserResponse {

    private Boolean success;
    private String userName;
    private Double currentBalance;
    private String lastRecharge;
    private Integer totalTrips;

    public BalanceUserResponse(){}

    public BalanceUserResponse(Boolean success, String userName, Double currentBalance, String lastRecharge, Integer totalTrips) {
        this.success = success;
        this.userName = userName;
        this.currentBalance = currentBalance;
        this.lastRecharge = lastRecharge;
        this.totalTrips = totalTrips;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
