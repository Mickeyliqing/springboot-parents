package com.example.springboottools.tools;

import org.springframework.util.Assert;

public class AssertTools {
    /**
     * Assert 常用对于参数的判断，引用 org.springframework.util 下的
     * @param args
     */
    public static void main(String[] args) {
        String str = null;
        Assert.notNull(str, "str不能为空");
    }
}
