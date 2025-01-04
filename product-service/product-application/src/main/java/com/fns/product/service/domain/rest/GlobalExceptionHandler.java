package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@ControllerAdvice
public class GlobalExceptionHandler {

//    private String getCurrentTimestamp() {
//        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An unexpected error occurred: " + ex.getMessage(),
//                getCurrentTimestamp()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
}

