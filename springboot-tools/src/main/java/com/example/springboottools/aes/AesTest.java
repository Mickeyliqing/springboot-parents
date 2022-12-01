package com.example.springboottools.aes;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.var;

import java.nio.charset.StandardCharsets;

public class AesTest {

    public static byte[] strToUtf8ByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);
        return byteArray;
    }

    public static String utf8ByteArrayToStr(byte[] input) {
        String str = new String(input, StandardCharsets.UTF_8);
        return str;
    }

    final static String TheEncKey = "IPWV7nuYQAKxKZq0z0uiKPJvXRXdp9FC"; // 32位英文字母或者数字


    public static AES genAesTool(String iv) {
        // 使用默认的AES/ECB/PKCS5Padding
        var ssb = strToUtf8ByteArray(TheEncKey);
        var aesTool = new AES(Mode.CFB, Padding.ISO10126Padding, ssb, strToUtf8ByteArray(iv));
        return aesTool;
    }

    // 对数据进行加密
    public static String enc1(String input, String iv) {
        var aesTool = genAesTool(iv);
        var abcStr = aesTool.encrypt(strToUtf8ByteArray(input));
        String encodeB64 = Base64.encode(abcStr);
        return encodeB64;
    }

    // 对数据进行解密
    public static String dec1(String input, String iv) {
        var needDecBin = Base64.decode(input);
        var aesTool = genAesTool(iv);
        var need1 = aesTool.decrypt(needDecBin);
        return utf8ByteArrayToStr(need1);
    }

    // 测试方法
    public static void main(String[] args) {
        final String ivStr = RandomUtil.randomString(16);
        System.out.println("ivStr: " + ivStr);

        String text1 = enc1("{\"we\": 100}", ivStr);
        System.out.println("enc: " + text1);

        String text2 = dec1(text1, ivStr);
        System.out.println(text2);
    }

}
