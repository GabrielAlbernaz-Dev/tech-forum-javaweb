package com.gabrielalbernazdev.techforumjavaweb.post.domain.exception;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.DomainException;

public class InvalidContentException extends DomainException {
    public InvalidContentException(String message) {
        super(message);
    }
}
