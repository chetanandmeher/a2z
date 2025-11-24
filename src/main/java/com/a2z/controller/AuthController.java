package com.a2z.controller;

import com.a2z.enums.USER_ROLE;
import com.a2z.model.VerificationCode;
import com.a2z.repository.UserRepository;
import com.a2z.request.LoginOtpRequest;
import com.a2z.request.LoginRequest;
import com.a2z.response.ApiResponse;
import com.a2z.response.AuthResponse;
import com.a2z.response.SignupRequest;
import com.a2z.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chetanand Meher
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest signupRequest) throws Exception {

        String jwt = authService.createUser(signupRequest);

        AuthResponse response = AuthResponse.builder()
                .jwt(jwt)
                .message("register successfully")
                .role(USER_ROLE.CUSTOMER)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(
            @RequestBody LoginOtpRequest request) throws Exception {

        authService.sendLoginOtp(request.getEmail(), request.getRole());

        ApiResponse response = ApiResponse.builder()
                .message("OTP sent to email successfully")
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest) throws Exception {

        AuthResponse response = authService.loginUser(loginRequest);

        return ResponseEntity.ok(response);

    }

}
