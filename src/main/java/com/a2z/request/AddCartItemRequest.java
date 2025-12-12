package com.a2z.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chetanand Meher
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCartItemRequest {
    private Long productId;
    private String size;
    private Integer quantity;
}
