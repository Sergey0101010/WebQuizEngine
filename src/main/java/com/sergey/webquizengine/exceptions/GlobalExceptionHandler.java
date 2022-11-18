package com.sergey.webquizengine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchQuizException.class)
    public ResponseEntity<CustomErrorMessage> handleNoSuchQuizException(
            NoSuchQuizException exception,
            WebRequest webRequest
    ) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                exception.getMessage(),
                webRequest.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(customErrorMessage, HttpStatus.NOT_FOUND);
    }

}
