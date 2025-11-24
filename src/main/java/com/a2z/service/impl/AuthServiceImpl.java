package com.a2z.service.impl;

import com.a2z.config.JwtProvider;
import com.a2z.enums.USER_ROLE;
import com.a2z.model.Cart;
import com.a2z.model.Seller;
import com.a2z.model.User;
import com.a2z.model.VerificationCode;
import com.a2z.repository.CartRepository;
import com.a2z.repository.SellerRepository;
import com.a2z.repository.UserRepository;
import com.a2z.repository.VerificationCodeRepository;
import com.a2z.request.LoginRequest;
import com.a2z.response.AuthResponse;
import com.a2z.response.SignupRequest;
import com.a2z.service.AuthService;
import com.a2z.service.EmailService;
import com.a2z.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Chetanand Meher
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private static final Logger log =LoggerFactory.getLogger(CustomUserServiceImpl.class);
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX = "seller_";
    private static final String SIGNING_PREFIX = "signing_";
    @Override
    public String createUser(SignupRequest req) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Invalid OTP...");
        }

        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            User newUser = User.builder()
                    .email(req.getEmail())
                    .firstName(req.getFirstName())
                    .lastName(req.getLastName())
                    .role(USER_ROLE.CUSTOMER)
                    .mobile("1234567890")
                    .password(passwordEncoder.encode(req.getOtp()))
                    .build();
            user = userRepository.save(newUser);

            Cart cart = Cart.builder()
                    .user(user)
                    .build();
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    /**
     * @param email
     * @param role
     * @throws Exception
     */
    @Override
    public void sendLoginOtp(String email, USER_ROLE role) throws Exception {

        log.info("email in login Otp: {}",  email)
        ;
        if (email.startsWith(SIGNING_PREFIX)) {
            email = email.substring(SIGNING_PREFIX.length());

            if (role.equals(USER_ROLE.SELLER)){
                Seller seller = sellerRepository.findByEmail(email);
                if (seller == null) {
                    throw new Exception("Seller not found with email: " + email);
                }
            }
            else{
                User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new Exception("User not found with email: " + email);
                }
            }

        }
        // Generate OTP
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
        if(verificationCode != null){
            verificationCodeRepository.delete(verificationCode);
        }

        String otp = OtpUtil.generateOtp();
        VerificationCode newVerificationCode = VerificationCode.builder()
                .email(email)
                .otp(otp)
                .build();
        verificationCodeRepository.save(newVerificationCode);

        String subject = "a2z login/signup otp";
        String body = "Your OTP for login/signup is: " + otp;

        emailService.sendVerificationOtpEmail(email, otp, subject, body);

    }

    /**
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public AuthResponse     loginUser(LoginRequest req) throws Exception {
        String username = req.getEmail();
        String otp = req.getOtp();

        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = AuthResponse.builder()
                .jwt(jwt)
                .message("Login successful")
                .build();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        String actualUsername = username.substring(SELLER_PREFIX.length());
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(actualUsername);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new BadCredentialsException("Invalid OTP");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
    }


}
