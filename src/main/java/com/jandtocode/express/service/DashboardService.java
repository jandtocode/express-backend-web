package com.jandtocode.express.service;

import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;
import com.jandtocode.express.dto.response.RechargeCardCalculateResponse;

public interface DashboardService {
    DashboardDefaultResponse getDashboardDefaultResponse(Long userId);
    BalanceUserResponse getBalanceByUserId(Long userId);

    RechargeCardCalculateResponse getInfoRechargeCardCalculate(Long userId, String typePayment, String bank,
                                                               String name, String lastName, Double valueRecharge);
}
