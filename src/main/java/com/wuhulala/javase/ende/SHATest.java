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
 * description: SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，被广泛地应用于电子商务等信息安全领域。
 * 虽然，SHA与MD5通过碰撞法都被破解了， 但是SHA仍然是公认的安全加密算法，较之MD5更为安全。

 */

public class SHATest {
    private static final Logger logger = LoggerFactory.getLogger(SHATest.class);

    public static final String KEY_SHA = "SHA";
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data = "test";
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data.getBytes());
        System.out.println(new BASE64Encoder().encode(sha.digest()));
        //qUqP5cyxm6YcTAhz05Hph5gvu9M=
    }
}
