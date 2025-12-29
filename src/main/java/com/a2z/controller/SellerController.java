package com.a2z.controller;


import com.a2z.enums.AccountStatus;
import com.a2z.exception.SellerException;
import com.a2z.model.Seller;
import com.a2z.model.SellerReport;
import com.a2z.model.VerificationCode;
import com.a2z.repository.VerificationCodeRepository;
import com.a2z.request.LoginRequest;
import com.a2z.response.AuthResponse;
import com.a2z.service.AuthService;
import com.a2z.service.EmailService;
import com.a2z.service.SellerReportService;
import com.a2z.service.SellerService;
import com.a2z.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private static final Logger log = LoggerFactory.getLogger(SellerController.class);
    private final AuthService authService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final SellerService sellerService;
    private final EmailService emailService;
    private final SellerReportService  sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(
            @RequestBody LoginRequest request) throws Exception {
        String otp = request.getOtp();
        String email = request.getEmail();

        request.setEmail("seller_"+email);
        AuthResponse authResponse = authService.loginUser(request);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {

        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Invalid OTP...");
        }
        Seller verifiedSeller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return ResponseEntity.ok(verifiedSeller);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller registeredSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();
        VerificationCode newVerificationCode = VerificationCode.builder()
                .email(seller.getEmail())
                .otp(otp)
                .build();
        verificationCodeRepository.save(newVerificationCode);

        String subject  = "A2Z Email Verification code";
        String body = "Welcome to A2Z, Verify your account by clicking on the link: ";
        String frontendUrl = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(), newVerificationCode.getOtp(), subject, body  + frontendUrl);
        return new ResponseEntity<>(registeredSeller,  HttpStatus.CREATED);
    }

    @GetMapping("/email-verification/{email}")
    public ResponseEntity<String> getEmailVerificationCode(
            @PathVariable String email) throws Exception {
        String otp = OtpUtil.generateOtp();
        VerificationCode newVerificationCode = VerificationCode.builder()
                .email(email)
                .otp(otp)
                .build();
        verificationCodeRepository.save(newVerificationCode);

        String subject  = "A2Z Email Verification code";
        String body = "Welcome to A2Z, Verify your account by clicking on the link: ";
        String frontendUrl = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(email, newVerificationCode.getOtp(), subject, body  + frontendUrl);
        return new ResponseEntity<>("Email Verification OTP sent successfully",  HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }


    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerProfile(@RequestHeader("Authorization") String jwt) throws Exception {

        log.info("****************JWT in getSellerProfile: \n{}", jwt);

        Seller seller = sellerService.getSellerProfileByJwt(jwt);
        return ResponseEntity.ok(seller);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException{
        // Implementation for generating seller report goes here
        Seller seller = sellerService.getSellerProfileByJwt(jwt);
        SellerReport sellerReport = sellerReportService.getSellerReportBySellerId(seller.getId());
        return ResponseEntity.ok(sellerReport);
    }

    @GetMapping()
    public ResponseEntity<List<Seller>> getAllSellersByStatus(
            @RequestParam(value = "status", required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllSellerByAccountStatus(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller) {

        Seller existingSeller = sellerService.getSellerProfileByJwt(jwt);
        Seller updatedSeller = sellerService.updateSeller(existingSeller.getId(), seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }



}
