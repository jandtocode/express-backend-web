package com.jandtocode.express.repository;

import com.jandtocode.express.entity.User;

public interface RegisterUserRepository {

    boolean existsByIdentification(String identification);
    User save(User user);
}
