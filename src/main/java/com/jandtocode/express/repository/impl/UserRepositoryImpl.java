package com.jandtocode.express.repository.impl;

import com.jandtocode.express.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public Map<String, String> findCredentialsByIdentification(String identification) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT u.id, u.identification, u.password, u.name, u.lastName, u.isBlocked, u.failedAttempts " +
                            "FROM User u WHERE u.identification = :identification"
            );
            query.setParameter("identification", identification);

            Object[] result = (Object[]) query.getSingleResult();

            Map<String, String> credentials = new HashMap<>();
            credentials.put("id", result[0].toString());
            credentials.put("identification", (String) result[1]);
            credentials.put("password", (String) result[2]);
            credentials.put("name", (String) result[3]);
            credentials.put("lastName", (String) result[4]);
            credentials.put("isBlocked", result[5].toString());
            credentials.put("failedAttempts", result[6].toString());

            return credentials;
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    @Transactional
    public void updateFailedAttempts(Integer userId, Integer failedAttempts) {

        entityManager.createQuery(
                        "UPDATE User u SET u.failedAttempts = :failedAttempts WHERE u.id = :userId"
                )
                .setParameter("failedAttempts", failedAttempts)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void blockUser(Integer userId) {
        entityManager.createQuery(
                        "UPDATE User u SET u.isBlocked = true WHERE u.id = :userId"
                )
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void resetFailedAttempts(Integer userId) {
        entityManager.createQuery(
                        "UPDATE User u SET u.failedAttempts = 0 WHERE u.id = :userId"
                )
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
