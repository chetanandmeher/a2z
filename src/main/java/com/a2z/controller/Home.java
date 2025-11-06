package com.a2z.controller;


import com.a2z.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chetanand Meher
 */
@RestController
public class Home {

    @GetMapping
    public ApiResponse homeControllerHandler(){
        return ApiResponse.builder()
                .message("Welcome to A2Z Application")
                .build();



    }
}
