package com.a2z.service.impl;

import com.a2z.config.JwtProvider;
import com.a2z.model.User;
import com.a2z.repository.UserRepository;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwt(jwt);

        return this.findUserByEmail(email);
    }


    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("User not found with email: " + email);
        }

        return user;
    }
}
