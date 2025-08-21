package com.gabrielalbernazdev.techforumjavaweb.post.domain.exception;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;

public class InvalidTitleException extends DomainException {
    public InvalidTitleException(String message) {
        super(message);
    }
}
