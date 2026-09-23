package com.jandtocode.express.service;

import com.jandtocode.express.dto.response.LoginResponse;
import com.jandtocode.express.dto.response.RegisterUserResponse;

public interface AuthService {

    LoginResponse login(String identification, String password);
    RegisterUserResponse register(String name, String lastName, String identification, String password, String confirmPassword);

}
