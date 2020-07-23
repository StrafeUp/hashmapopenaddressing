package com.strafeup;

/**
 * Custom exception that extends RuntimeException.
 * Trying to get element by key which is absent in the inner array will result in this exception.
 */
public class ElementIsNotPresentException extends RuntimeException {
    /**
     * Basic zero argument constructor
     */
    public ElementIsNotPresentException() {
    }

    /**
     * Constructor for special cases, with message parameters
     *
     * @param message custom message of an exception
     */
    public ElementIsNotPresentException(String message) {
        super(message);
    }
}
