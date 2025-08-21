package com.gabrielalbernazdev.techforumjavaweb.post.domain.vo;

import com.gabrielalbernazdev.techforumjavaweb.post.domain.exception.InvalidTitleException;

import java.util.Objects;

public class Title {
    private final String value;

    private Title(String value) {
        this.value = value;
    }

    public static Title of(String value) {
        validateTitle(value);
        return new Title(value.trim());
    }

    private static void validateTitle(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidTitleException("Title cannot be empty");
        }
        if (value.trim().length() < 5) {
            throw new InvalidTitleException("Title must be at least 5 characters long");
        }
        if (value.trim().length() > 100) {
            throw new InvalidTitleException("Title cannot be longer than 100 characters");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return Objects.equals(value, title.value);
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
