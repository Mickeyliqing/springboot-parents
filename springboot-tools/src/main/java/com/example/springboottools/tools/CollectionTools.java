package com.example.springboottools.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTools {
    /**
     * Collections 常用 集合的操作，但这里引用的是 java.util 下的
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Collections.sort(list);
        System.out.println(list);
    }
}
