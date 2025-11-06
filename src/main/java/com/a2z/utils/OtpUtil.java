package com.a2z.utils;

import java.util.Random;

/**
 * @author Chetanand Meher
 */
public class OtpUtil {

    public static String generateOtp() {
        int otpLength = 6;

        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            int digit = random.nextInt(10);
            otp.append(digit);
        }
        return otp.toString();
    }
}
