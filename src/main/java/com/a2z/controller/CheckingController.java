package com.a2z.controller;

import com.a2z.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chetanand Meher
 */
@RestController
@Slf4j
public class CheckingController {

    @GetMapping("/checking-endpoint")
    public ResponseEntity<ApiResponse> dummyEndpoint() {
        log.info("madardhood chal na bsdk");
        ApiResponse response = ApiResponse.builder()
                .message("Hogya milgyai tassali")
                .build();
        return ResponseEntity.ok(response);
    }
}
