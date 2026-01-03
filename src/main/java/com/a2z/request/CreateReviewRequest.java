package com.a2z.request;

import lombok.Data;

import java.util.List;

/**
 * @author Chetanand Meher
 */

@Data
public class CreateReviewRequest {

    private String reviewText;
    private double reviewRating;
    private List<String> productImages;
}
