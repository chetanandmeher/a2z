package com.a2z.enums;

public enum PaymentStatus {
    /**
     * Payment is initiated but not yet processed.
     * Initial state when payment request is created.
     */
    PENDING,

    /**
     * Payment is being processed by the payment gateway.
     * Intermediate state during payment verification.
     */
    PROCESSING,

    /**
     * Payment has been successfully completed.
     * Final state for successful payment transaction.
     */
    COMPLETED,

    /**
     * Payment has failed due to various reasons.
     * Final state for unsuccessful payment attempts.
     */
    FAILED
}
