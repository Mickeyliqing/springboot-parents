package com.example.springboottools.tools;
import org.apache.commons.lang3.BooleanUtils;

public class BooleanUtilsTools {
    /**
     * BooleanUtils 常用于 Boolean 类型的操作，这里引用的是 org.apache.commons.lang3 下的
     * @param args
     */
    public static void main(String[] args) {
        Boolean b = false;
        System.out.println(BooleanUtils.isFalse(b));
    }
}
