package com.example.springboottools.tools;

import com.example.springboottools.model.User;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTools {
    /**
     * org.springframework.beans 包下 BeanUtils 操作单个或者多个对象
     * @param args
     */
    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("AA");
        user1.setAge(23);

        User user2 = new User();
        BeanUtils.copyProperties(user1, user2);
        System.out.println(user2);
    }
}
