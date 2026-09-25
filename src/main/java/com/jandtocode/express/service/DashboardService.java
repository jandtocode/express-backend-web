package com.jandtocode.express.service;

import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;

public interface DashboardService {
    DashboardDefaultResponse getDashboardDefaultResponse(Long userId);
    BalanceUserResponse getBalanceByUserId(Long userId);
}
