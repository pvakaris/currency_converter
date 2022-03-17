package com.example.currency_converter.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The purpose of this class is to show custom exception response to the user.
 *
 * @version 1.0
 * @author Vakaris Paulavicius
 */
public class APIException {
    private final String message;
    private final HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;

    /**
     * APIException constructor.
     * @param message A message stating what went wrong.
     * @param httpStatus HTTP status of the exception.
     * @param timestamp Time when the exception occurred.
     */
    public APIException(String message, HttpStatus httpStatus, LocalDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    /**
     * Get message text.
     * @return Message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get HTTP status.
     * @return HTTP status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Get time when exception occurred.
     * @return Exception time.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
