package com.legolas.cvpasrser.controller.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ControllerExceptionHandler {


    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleResponseStatusException(HttpStatusCodeException ex) {
        log.error("Client exception: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse
                        .builder()
                        .reasonCodes(List.of(ReasonCode
                                .builder()
                                .code(ex.getStatusCode().value())
                                .message(ex.getMessage())
                                .build()))
                        .build()
                );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        log.error("Unexpected error occurred :" + exception.getLocalizedMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse
                        .builder()
                        .reasonCodes(List.of(ReasonCode
                                .builder()
                                .code(500)
                                .message(String.format("Unexpected error occurred : %s", exception.getLocalizedMessage()))
                                .build()))
                        .build()
                );
    }
}

