package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.StringTokenizer;

public class HashUtils {
    
    public static final String hashPassword(String password) {
        if (password == null) {
            throw new NullPointerException();
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] hashPasswordByte = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashPasswordString = Base64.getEncoder().encodeToString(hashPasswordByte);
            String saltString = Base64.getEncoder().encodeToString(salt);

            return saltString + hashPasswordString;
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static final boolean checkPassword(String password, String hashPassword) {
        if (password == null || hashPassword == null) {
            throw new NullPointerException();
        }

        StringTokenizer tokenizer = new StringTokenizer(hashPassword, "==");
        String saltString = tokenizer.hasMoreElements() ? tokenizer.nextToken() : "";
        String hashString = tokenizer.hasMoreElements() ? tokenizer.nextToken() : "";

        if (saltString.isEmpty() || hashString.isEmpty()) {
            return false;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(Base64.getDecoder().decode(saltString + "=="));
            byte[] hashPasswordByte = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashPasswordString = Base64.getEncoder().encodeToString(hashPasswordByte);

            return hashPasswordString.equals(hashString + "==");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static final String extractHashPassword(String hashString) {
        if (hashString == null) {
            throw new NullPointerException();
        }

        StringTokenizer tokenizer = new StringTokenizer(hashString, "==");
        
        if (!tokenizer.hasMoreTokens()) {
            return null;
        }

        tokenizer.nextElement();
        return tokenizer.hasMoreElements() ? (tokenizer.nextToken() + "==") : null;
    }
}
