package com.a2z.request;

import com.a2z.enums.USER_ROLE;
import lombok.Data;

/**
 * @author Chetanand Meher
 */

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
