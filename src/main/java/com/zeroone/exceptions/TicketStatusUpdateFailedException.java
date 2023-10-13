package com.zeroone.exceptions;

public class TicketStatusUpdateFailedException extends RuntimeException {
    public TicketStatusUpdateFailedException(String message) {
        super(message);
    }
}
