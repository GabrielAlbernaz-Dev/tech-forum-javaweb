package com.gabrielalbernazdev.techforumjavaweb.post.domain.vo;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.exception.InvalidContentException;

import java.util.Objects;

public class Content {
    private final String value;

    private Content(String value) {
        this.value = value;
    }

    public static Content of(String value) {
        validateContent(value);
        return new Content(value.trim());
    }

    private static void validateContent(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidContentException("Content cannot be empty");
        }
        if (value.trim().length() < 10) {
            throw new InvalidContentException("Content must be at least 10 characters long");
        }
        if (value.trim().length() > 5000) {
            throw new InvalidContentException("Content cannot be longer than 5000 characters");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(value, content.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
