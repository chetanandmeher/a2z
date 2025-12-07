package com.a2z.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
       /** Title of the product. */
    private String title;

    /** Short description of the product. */
    private String description;

    /** Maximum Retail Price of the product. */
    private int mrpPrice;

    /** Selling price offered to the customers. */
    private int sellingPrice;

    /** Color of the product. */
    private String color;

    /** List of image URLs or image identifiers for the product. */
    private List<String> images;

    /** Category indicating the target audience (Man/Woman/Kid). */
    private String category1;

    /** Type of wear (top wear/bottom wear/footwear). */
    private String category2;

    /** Main category (main shirt/main pants/main shoes). */
    private String category3;

    /** Size or size variant of the product. */
    private String size;
}



