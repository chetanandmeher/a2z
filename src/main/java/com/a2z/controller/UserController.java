package com.a2z.controller;

import com.a2z.model.User;
import com.a2z.repository.UserRepository;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        // Implementation to get user profile
        User user = userService.findUserByJwt(jwt);
        return ResponseEntity.ok(user);
    }

}
