package com.jandtocode.express.repository;

import com.jandtocode.express.entity.User;

import java.util.Map;

public interface UserRepository {

    Map<String, String> findCredentialsByIdentification(String identification);
    void updateFailedAttempts(Integer userId, Integer failedAttempts);
    void blockUser(Integer userId);
    void resetFailedAttempts(Integer userId);
}
