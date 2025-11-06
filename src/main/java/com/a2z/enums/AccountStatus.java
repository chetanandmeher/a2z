package com.a2z.enums;

/**
 * @author Chetanand Meher
 */

public enum AccountStatus {
    /**
     * Account is created but not yet verified.
     * Initial state for newly created accounts pending verification.
     */
    PENDING_VERIFICATION,

    /**
     * Account is active and in good standing.
     * User has full access to all account features.
     */
    ACTIVE,

    /**
     * Account is temporarily suspended, possibly due to violations.
     * User access is temporarily restricted until issues are resolved.
     */
    SUSPENDED,

    /**
     * Account is deactivated, user may have chosen to deactivate it.
     * User-initiated account deactivation with option to reactivate.
     */
    DEACTIVATED,

    /**
     * Account is permanently banned due to severe violations.
     * No possibility of reactivation due to policy violations.
     */
    BANNED,

    /**
     * Account is permanently closed, possibly at user request.
     * Final state with no option for reactivation.
     */
    CLOSED

}

