package com.jandtocode.express.service;

import com.jandtocode.express.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(String identification, String password);

}
