package me.williamgalvin.customauth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashUtil {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String hashString(String str) {
        return PASSWORD_ENCODER.encode(str);
    }

    public static boolean equals(String raw, String hash) {
        return PASSWORD_ENCODER.matches(raw, hash);
    }

}
