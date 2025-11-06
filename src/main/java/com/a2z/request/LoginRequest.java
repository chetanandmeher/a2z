package com.a2z.request;

import lombok.*;

/**
 * @author Chetanand Meher
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String otp;

}
