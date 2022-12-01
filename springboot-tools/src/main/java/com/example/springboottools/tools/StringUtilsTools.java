package com.example.springboottools.tools;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsTools {
    /**
     * StringUtils 常用来操作 String 类型的数据，这里引用的是 org.apache.commons.lang3 下的
     * 优先推荐使用 isBlank 和 isNotBlank 方法，因为它会把" "也考虑进去
     * @param args
     */
    public static void main(String[] args) {
        String str = "abc";
        if (StringUtils.isNotBlank(str)) {
            System.out.println(str);
        }
    }
}
