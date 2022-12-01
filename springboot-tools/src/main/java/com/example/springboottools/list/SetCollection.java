package com.example.springboottools.list;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetCollection {
    public static void main(String[] args) {

        /**
         * Set 内地数据元素特点，无序，无下标，元素不可重复
         * Set 是一个接口，父类是 Collection，而 Collection 的父类是 Iterable
         * HashSet 是 Set 接口的实现类
         * 对应输出的数据形式是：[]
         */
        Set<String> set = new HashSet<>();
        set.add("3");
        set.add("1");
        set.add("2");

        System.out.println(set);

        /**
         * TreeSet 是 Set 接口的实现类, 和 HashSet 最大的不同就是 TreeSet 内地数据是有序的
         * 对应输出的数据形式是：[]
         */
        Set<String> set1 = new TreeSet<>();
        set1.add("1");
        set1.add("2");
        set1.add("3");

        System.out.println(set1);
    }
}
