package com.jandtocode.express.repository.impl;

import com.jandtocode.express.repository.BalanceUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceUserRepositoryImpl implements BalanceUserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Map<String, Object> getBalanceInfoByUserId(Long userId) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT b.currentBalance, b.accumulatedRecharges, b.lastRecharge " +
                            "FROM Balance b WHERE b.user.id = :userId"
            );
            query.setParameter("userId", userId);

            Object[] result = (Object[]) query.getSingleResult();

            Map<String, Object> balanceInfo = new HashMap<>();
            balanceInfo.put("currentBalance", result[0]);
            balanceInfo.put("accumulatedRecharges", result[1]);
            balanceInfo.put("lastRecharge", result[2]);

            return balanceInfo;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Map<String, Object> getUserNameById(Long userId) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT u.name FROM User u WHERE u.id = :userId"
            );
            query.setParameter("userId", userId);

            String name = (String) query.getSingleResult();

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("name", name);

            return userInfo;
        } catch (NoResultException e) {
            return null;
        }
    }

}