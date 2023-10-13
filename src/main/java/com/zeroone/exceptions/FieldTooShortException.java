package com.zeroone.exceptions;

public class FieldTooShortException extends ValidationException {
    public FieldTooShortException(String message) {
        super(message);
    }
}