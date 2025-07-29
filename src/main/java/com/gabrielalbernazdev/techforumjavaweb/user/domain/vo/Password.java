package com.gabrielalbernazdev.techforumjavaweb.user.domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.exception.InvalidPasswordException;
import org.mindrot.jbcrypt.BCrypt;

public final class Password {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 64;
    private static final Pattern STRONG_PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    private final String value;

    private Password(String hash) {
        this.value = Objects.requireNonNull(hash, "Password hash cannot be null");
    }

    public static Password fromPlain(String plainPassword) {
        validatePlain(plainPassword);
        String hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        return new Password(hash);
    }

    public static Password fromHash(String hash) {
        return new Password(hash);
    }

    public boolean matches(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.value);
    }

    public String getValue() {
        return value;
    }

    private static void validatePlain(String value) {
        Objects.requireNonNull(value, "Password cannot be null");
        String trimmed = value.trim();
        if (trimmed.length() < MIN_LENGTH) {
            throw new InvalidPasswordException("Password must be at least " + MIN_LENGTH + " characters");
        }
        if (trimmed.length() > MAX_LENGTH) {
            throw new InvalidPasswordException("Password exceeds maximum length (" + MAX_LENGTH + ")");
        }
        if (!STRONG_PASSWORD_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidPasswordException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character");
        }
    }

    @Override
    public String toString() {
        return "Password{hash='[PROTECTED]'}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

