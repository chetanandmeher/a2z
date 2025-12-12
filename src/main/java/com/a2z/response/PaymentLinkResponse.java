package com.a2z.response;

import lombok.*;

/**
 * @author Chetanand Meher
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentLinkResponse {
    private String paymentLinkUrl;
    private String paymentLinkId;
}
