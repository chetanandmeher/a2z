package com.a2z.model;

import com.a2z.enums.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user entity in the system.
 * This class stores user information including personal details, role, addresses, and coupon usage.
 *
 * @author Chetanand Meher
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    /**
     * The unique identifier for the user.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    /**
     * The user's password.
     * Write-only property for security purposes.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The user's email address.
     * Used for communication and authentication.
     */
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
    @OneToMany
    private Set<Address> addresses = new HashSet<>();

    /**
     * Collection of coupons that have been used by the user.
     * Tracks coupon usage history.
     */
    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();

    private LocalDate createdAt;

    private LocalDate updatedAt;

}
