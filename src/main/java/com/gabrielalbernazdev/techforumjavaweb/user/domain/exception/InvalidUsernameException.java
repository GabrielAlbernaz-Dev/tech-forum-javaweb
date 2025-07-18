package com.gabrielalbernazdev.techforumjavaweb.user.domain.exception;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;

public class InvalidUsernameException extends DomainException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
