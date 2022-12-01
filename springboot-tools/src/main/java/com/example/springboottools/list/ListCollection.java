package com.example.springboottools.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListCollection {

    public static void main(String[] args) {
        /**
         * List 内地数据元素特点，有序，有下标，元素可重复
         * List 是一个接口，父类是 Collection，而 Collection 的父类是 Iterable
         * ArrayList 是 List 接口的实现类
         * 对应输出的数据形式是：[]
         */
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println(list);

        /**
         * LinkedList 是 List 接口的实现类 和 ArrayList 最大的区别就是底层的实现
         * ArrayList 的底层是数据
         * LinkedList 的底层是双向链表
         * 对应输出的数据形式是：[]
         */
        List<String> list1 = new LinkedList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        System.out.println(list1);
    }
}
