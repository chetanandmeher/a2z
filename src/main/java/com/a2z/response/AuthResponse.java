package com.a2z.response;

import com.a2z.enums.USER_ROLE;
import lombok.*;

/**
 * @author Chetanand Meher
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
