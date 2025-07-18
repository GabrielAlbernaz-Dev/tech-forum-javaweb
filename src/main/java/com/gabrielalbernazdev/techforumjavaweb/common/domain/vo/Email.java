package com.gabrielalbernazdev.techforumjavaweb.common.domain.vo;

import com.gabrielalbernazdev.techforumjavaweb.common.exception.InvalidEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {
    private static final Pattern RFC_5322_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
    );

    private final String value;

    private Email(String value) {
        this.value = validate(value);
    }

    public static Email of(String value) throws InvalidEmailException {
        try {
            return new Email(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidEmailException(e.getMessage());
        }
    }

    public String getValue() {
        return value;
    }

    private String validate(String value) {
        Objects.requireNonNull(value, "Email cannot be null");

        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (trimmed.length() > 254) {
            throw new IllegalArgumentException("Email exceeds maximum length (254 chars)");
        }
        if (!RFC_5322_PATTERN.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Invalid email format according to RFC 5322");
        }
        return trimmed.toLowerCase();
    }

    @Override
    public String toString() {
        return "Email{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
