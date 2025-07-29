package com.gabrielalbernazdev.techforumjavaweb.user.domain.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
