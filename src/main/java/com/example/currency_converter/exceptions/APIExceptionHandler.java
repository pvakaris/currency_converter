package com.example.currency_converter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

/**
 * This class is responsible for handling exceptions.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
@ControllerAdvice
public class APIExceptionHandler {

    /**
     * Handles CustomException.
     * @param exception Exception that occurred.
     * @return Response entity which to show to the user.
     */
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        APIException apiException = new APIException(exception.getMessage(), status, LocalDateTime.now());
        return new ResponseEntity<>(apiException, status);
    }
}
