package com.challenge.ml.exception;

/**
 * Class used for invalid information.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
public class InvalidDataException extends Exception {

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}

