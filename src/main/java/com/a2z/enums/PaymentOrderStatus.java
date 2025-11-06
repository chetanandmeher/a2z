package com.a2z.enums;

import lombok.Builder;

/**
 * @author Chetanand Meher
 */

public enum PaymentOrderStatus {
    /**
     * Payment order is created but transaction is not yet complete.
     * Initial state when payment is initiated.
     */
    PENDING,

    /**
     * Payment order has been successfully processed.
     * Final state for successful payment transactions.
     */
    SUCCESS,

    /**
     * Payment order has failed to process.
     * Final state for unsuccessful payment transactions.
     */
    FAILED
}
