package com.a2z.service;

import com.a2z.model.User;

/**
 * @author Chetanand Meher
 */
public interface UserService {

    User findUserByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
