package com.jandtocode.express.repository.impl;

import com.jandtocode.express.repository.RechargeCardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RechargeCardRepositoryImpl implements RechargeCardRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Map<String, Object> getInfoByUserId(Integer userId) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT u.name, u.lastName, " +
                            "d.currentBalance, d.accumulatedRecharges, d.lastRecharge, " +
                            "d.typePayment, d.entityPayment, d.valueRecharge, d.applyBonus, d.valueBonus " +
                            "FROM User u LEFT JOIN Dashboard d ON u.id = d.user.id " +
                            "WHERE u.id = :userId"
            );
            query.setParameter("userId", userId);

            Object[] result = (Object[]) query.getSingleResult();

            Map<String, Object> userInfo = new HashMap<>();

            // Datos de user_express
            userInfo.put("name", result[0]);
            userInfo.put("lastName", result[1]);

            // Datos de dashboard_info
            userInfo.put("currentBalance", result[2] != null ? result[2] : 0.0);
            userInfo.put("accumulatedRecharges", result[3] != null ? result[3] : 0);
            userInfo.put("lastRecharge", result[4]);
            userInfo.put("typePayment", result[5]);
            userInfo.put("entityPayment", result[6]);
            userInfo.put("valueRecharge", result[7]);
            userInfo.put("applyBonus", result[8]);
            userInfo.put("valueBonus", result[9]);

            return userInfo;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void saveRechargeCalculation(Integer userId, String type_payment, String entity_payment,
                                        Double value_recharge, Boolean apply_bonus, Double value_bonus) {

        entityManager.createQuery(
                        "UPDATE Dashboard d SET " +
                                "d.typePayment = :typePayment, " +
                                "d.entityPayment = :entityPayment, " +
                                "d.valueRecharge = :valueRecharge, " +
                                "d.applyBonus = :applyBonus, " +
                                "d.valueBonus = :valueBonus, " +
                                "d.updatedAt = CURRENT_TIMESTAMP " +
                                "WHERE d.user.id = :userId"
                )
                .setParameter("typePayment", type_payment)
                .setParameter("entityPayment", entity_payment)
                .setParameter("valueRecharge", value_recharge)
                .setParameter("applyBonus", apply_bonus)
                .setParameter("valueBonus", value_bonus)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateUserBalanceAfterRecharge(
            Integer userId,
            Double currentBalance,
            Integer accumulated_recharges) {

        entityManager.createQuery(
                        "UPDATE Dashboard d SET " +
                                "d.currentBalance = :currentBalance, " +
                                "d.accumulatedRecharges = :accumulatedRecharges, " +
                                "d.lastRecharge = CURRENT_DATE, " +
                                "d.updatedAt = CURRENT_TIMESTAMP " +
                                "WHERE d.user.id = :userId"
                )
                .setParameter("currentBalance", currentBalance)
                .setParameter("accumulatedRecharges", accumulated_recharges)
                .setParameter("userId", userId)
                .executeUpdate();
    }

}