package com.a2z.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author Chetanand Meher
 */

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorDetails> sellerExceptionHandler(
            SellerException sellerException, WebRequest request
    ){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .error(sellerException.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(errorDetails);
    }

    public ResponseEntity<ErrorDetails> productExceptionHandler(
            Exception exception, WebRequest request
    ){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .error(exception.getMessage())
                .details(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(errorDetails);
    }



}
