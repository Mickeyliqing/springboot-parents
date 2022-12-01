package com.example.springboottools.tools;

import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

public class Base64UtilsTools {
    /**
     * org.springframework.util 包下的 Base64Utils 工具类完成对参数的加密和解密
     * @param args
     */
    public static void main(String[] args) {
        String str = "abc";
        String encode = new String(Base64Utils.encode(str.getBytes()));
        System.out.println("加密后：" + encode);
        String decode = new String(Base64Utils.decode(encode.getBytes()), StandardCharsets.UTF_8);
        System.out.println("解密后：" + decode);
    }
}
