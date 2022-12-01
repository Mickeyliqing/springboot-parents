package com.example.springboottools.tools;



import com.example.springboottools.model.User;

import java.util.Objects;

public class ObjectsTools {
    /**
     * Objects 常用来对象的操作，这里引用的是 java.util 下
     */
    public static void main(String[] args) {
        User user = new User();
        if (Objects.nonNull(user)) {
            System.out.println("AA");
        }
    }
}
