package test;

import security.HashUtils;

public class TestJava {
    public static void main(String[] args) {
        String hash = HashUtils.hashPassword("admin");
        System.out.println(HashUtils.checkPassword("admin", hash));
        System.out.println(hash.length());
        System.out.println(hash);
    }
}
