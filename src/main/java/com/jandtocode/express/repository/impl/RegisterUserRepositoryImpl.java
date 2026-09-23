package com.jandtocode.express.repository.impl;

import com.jandtocode.express.entity.User;
import com.jandtocode.express.repository.RegisterUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterUserRepositoryImpl implements RegisterUserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean existsByIdentification(String identification) {
        Long count = (Long) entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.identification = :identification"
                )
                .setParameter("identification", identification)
                .getSingleResult();

        return count > 0;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setFailedAttempts(0);
        user.setIsBlocked(false);

        entityManager.persist(user);
        entityManager.flush();

        return user;
    }
}
