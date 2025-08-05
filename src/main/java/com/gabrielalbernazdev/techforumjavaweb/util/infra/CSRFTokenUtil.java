package com.gabrielalbernazdev.techforumjavaweb.util.infra;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CSRFTokenUtil {
    private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        return new BigInteger(130, random).toString(32);
    }
}
