package com.challenge.ml.exception;

/**
 * Class used for not found exceptions
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
