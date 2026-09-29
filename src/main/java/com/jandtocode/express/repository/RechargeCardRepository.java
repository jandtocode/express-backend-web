package com.jandtocode.express.repository;

import java.util.Map;

public interface RechargeCardRepository {

    Map<String, Object> getInfoByUserId(Integer userId);

    void updateUserInfoDashboard(Integer userId, String type_payment, String entity_payment, Double value_recharge,
                                 Boolean apply_bonus, Double value_bonus);

    void updateTravelsUser(Integer userId, Double current_balance, Integer accumaled_recharges);
}
