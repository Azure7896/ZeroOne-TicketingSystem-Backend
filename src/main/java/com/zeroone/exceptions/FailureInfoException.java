package com.zeroone.exceptions;

    public class FailureInfoException extends RuntimeException {

        public FailureInfoException(String message) {
            super(message);
        }

        public FailureInfoException(String message, Throwable cause) {
            super(message, cause);
        }

}
