package com.teskinfly.config;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class MyPasswordEncoder {
    MessageDigest messageDigest;
    {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encode(String password) {
        byte[] digest = messageDigest.digest(password.getBytes());
        return Hex.encodeHexString(digest);
    }
    public boolean match(String encodePassword,String password) {
        return encodePassword.equals(encode(password));
    }
}
