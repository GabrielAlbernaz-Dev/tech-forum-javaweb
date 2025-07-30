package com.gabrielalbernazdev.techforumjavaweb.user.domain.exception;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
