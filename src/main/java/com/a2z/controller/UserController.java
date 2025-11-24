package com.a2z.controller;

import com.a2z.model.User;
import com.a2z.repository.UserRepository;
import com.a2z.response.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        // Implementation to get user profile
        UserResponseDto userResponseDto = userService.findUserByJwt(jwt);
        return ResponseEntity.ok(userResponseDto);

    }

}
