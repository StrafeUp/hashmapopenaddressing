package com.strafeup;

public class ElementIsNotPresentException extends RuntimeException {
    public ElementIsNotPresentException() {
    }

    public ElementIsNotPresentException(String message) {
        super(message);
    }
}
