package com.a2z.response;

import com.a2z.enums.USER_ROLE;
import com.a2z.model.Address;
import com.a2z.model.Coupon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Chetanand Meher
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserResponseDto {

    private Long id;

    private String email;

    /**
     * The user's first name.
     */
    private String firstName;

    /**
     * The user's last name.
     */
    private String lastName;

    /**
     * The user's mobile phone number.
     */
    private String mobile;

    /**
     * The role assigned to the user.
     * Determines user permissions and access levels.
     */
    private USER_ROLE role;

    /**
     * Collection of addresses associated with the user.
     * Used for delivery and billing purposes.
     */

    private Set<Address> addresses = new HashSet<>();

    /**
     * Collection of coupons that have been used by the user.
     * Tracks coupon usage history.
     */
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();
}
