package com.jandtocode.express.dto.request;

public class BalanceUserRequest {

    private Long userId;

    public BalanceUserRequest(){}

    public BalanceUserRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
