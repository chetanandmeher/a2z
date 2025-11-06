package com.a2z.service;

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

    void sendLoginOtp(String email) throws Exception;

    AuthResponse loginUser(LoginRequest re) throws Exception;
}
