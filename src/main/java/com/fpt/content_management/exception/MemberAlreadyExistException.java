package com.fpt.content_management.exception;

public class MemberAlreadyExistException extends RuntimeException {

    public MemberAlreadyExistException(String message) {
        super(message);
    }
}
