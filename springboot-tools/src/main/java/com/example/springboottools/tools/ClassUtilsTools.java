package com.example.springboottools.tools;

import com.example.springboottools.model.User;
import org.springframework.util.ClassUtils;

public class ClassUtilsTools {
    /**
     * org.springframework.util 包下的 ClassUtils 操作对象相关的属性和方法
     * @param args
     */
    public static void main(String[] args) {
        String packageName = ClassUtils.getPackageName(User.class);
        System.out.println(packageName);
    }
}
