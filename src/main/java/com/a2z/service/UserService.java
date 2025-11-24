package com.a2z.service;

import com.a2z.model.User;
import com.a2z.response.UserResponseDto;

/**
 * @author Chetanand Meher
 */
public interface UserService {

    UserResponseDto findUserByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
