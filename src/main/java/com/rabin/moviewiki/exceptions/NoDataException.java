package com.rabin.moviewiki.exceptions;

public class NoDataException extends RuntimeException {

    public NoDataException(String messageId) {
        super(messageId);
    }
}
