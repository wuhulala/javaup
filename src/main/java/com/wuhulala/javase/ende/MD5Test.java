package com.wuhulala.javase.ende;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: 作甚的
 */

public class MD5Test {
    private static final Logger logger = LoggerFactory.getLogger(MD5Test.class);

    public static final String KEY_MD5 = "MD5";

    public static void main(String[] args) {
        String data = "test";
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(data.getBytes());
            byte[] result = md5.digest();
            System.out.println(new BASE64Encoder().encode(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //CY9rzUYh03PK3k6DJie09g==

    }
}
