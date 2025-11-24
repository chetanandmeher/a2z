package com.a2z.service;

import com.a2z.enums.USER_ROLE;
import com.a2z.request.LoginRequest;
import com.a2z.response.AuthResponse;
import com.a2z.response.SignupRequest;

/**
 * @author Chetanand Meher
 */
public interface AuthService {

    /**
     * @param req
     * @return String
     */
    String createUser(SignupRequest req) throws Exception;

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;

    AuthResponse loginUser(LoginRequest re) throws Exception;
}
