package com.gabrielalbernazdev.techforumjavaweb.user.domain.vo;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.exception.InvalidUsernameException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Username {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private final String value;

    private Username(String value) {
        this.value = validate(value);
    }

    public static Username of(String value) {
        return new Username(value);
    }

    private static String validate(String value) {
        Objects.requireNonNull(value, "Username cannot be null");

        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidUsernameException("Username cannot be empty");
        }

        if (!VALID_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidUsernameException(
                    "Username must be 3-20 alphanumeric characters or underscores"
            );
        }

        return trimmed.toLowerCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return value.equals(username.value);
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
