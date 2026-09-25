package com.jandtocode.express.repository;

import java.util.Map;

public interface BalanceUserRepository {
    Map<String, Object> getBalanceInfoByUserId(Long userId);
    Map<String, Object>  getUserNameById(Long userId);
}
