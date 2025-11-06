package com.a2z.enums;

/**
 * @author Chetanand Meher
 */

public enum OrderStatus {
    /**
     * Order is initially created but payment is pending.
     * Initial state when customer creates an order.
     */
    PENDING,

    /**
     * Order is successfully placed after payment confirmation.
     * Payment has been received and order is registered.
     */
    PLACED,

    /**
     * Order is confirmed by the seller.
     * Seller has acknowledged and started processing the order.
     */
    CONFIRMED,

    /**
     * Order has been shipped to the customer.
     * Products are in transit to delivery address.
     */
    SHIPPED,

    /**
     * Order has been successfully delivered to the customer.
     * Final state for successful order completion.
     */
    DELIVERED,

    /**
     * Order has been cancelled.
     * Either by customer before confirmation or due to other issues.
     */
    CANCELLED

}
