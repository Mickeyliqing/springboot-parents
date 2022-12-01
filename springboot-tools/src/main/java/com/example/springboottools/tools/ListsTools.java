package com.example.springboottools.tools;

import com.google.common.collect.Lists;

import java.util.List;

public class ListsTools {
    /**
     * Lists 常用于 List 的操作，这里引用的是 com.google.common.collect 下
     */
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        List<List<Integer>> partitionList = Lists.partition(list, 2);
        System.out.println(partitionList);
    }
}
