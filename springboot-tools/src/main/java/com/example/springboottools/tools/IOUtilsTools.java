package com.example.springboottools.tools;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class IOUtilsTools {
    /**
     * IOUtils 常用 IO 的操作，这里引用的是 org.apache.commons.io 下
     */
    public static void main(String[] args) throws IOException {
        String str = IOUtils.toString(new FileInputStream("/temp/a.txt"), StandardCharsets.UTF_8);
        System.out.println(str);
    }
}
