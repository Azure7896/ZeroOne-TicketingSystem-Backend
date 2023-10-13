package com.zeroone.exceptions;

public class PasswordTooShortException extends ValidationException {
    public PasswordTooShortException(String message) {
        super(message);
    }
}