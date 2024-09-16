package com.yourssu.blog.config.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Encrypt {

    private final String salt;

    public Encrypt(@Value("${security.encrypt.salt}") final String salt) {
        this.salt = salt;
    }

    public String encrypt(final String input) {
        return sha256(input + salt);
    }

    private String sha256(final String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(input.getBytes());
            return bytesToHex(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] result) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : result) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
