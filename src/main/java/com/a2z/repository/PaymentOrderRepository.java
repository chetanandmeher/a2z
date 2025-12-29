package com.a2z.repository;

import com.a2z.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {


    PaymentOrder findByPaymentLinkId(String paymentLinkId);

}
