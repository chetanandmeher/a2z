package com.a2z.response;

import lombok.*;

/**
 * @author Chetanand Meher
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String otp;
}
