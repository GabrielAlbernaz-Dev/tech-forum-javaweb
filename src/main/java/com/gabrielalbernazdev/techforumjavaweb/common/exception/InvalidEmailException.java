package com.gabrielalbernazdev.techforumjavaweb.common.exception;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException(String message) {
        super(message);
    }
}