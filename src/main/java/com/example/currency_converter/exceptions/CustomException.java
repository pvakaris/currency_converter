package com.example.currency_converter.exceptions;

/**
 * Custom exception with a message.
 *
 * @author Vakaris Paulavicius
 * @version 1.0
 */
public class CustomException extends RuntimeException {

    /**
     * CustomException constructor.
     * @param message Message of the exception.
     */
    public CustomException(String message) {
        super(message);
    }
}
