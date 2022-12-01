package com.example.springboottools.tools;


import org.apache.commons.codec.digest.DigestUtils;

public class DigestUtilsTools {
    /**
     * org.apache.commons.codec.digest 下的 DigestUtils 对数据加密
     * @param args
     */
    public static void main(String[] args) {
        String md5Hex = DigestUtils.md5Hex("哈哈哈");
        System.out.println(md5Hex);
    }
}
