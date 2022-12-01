package com.example.springboottools.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapCollection {
    public static void main(String[] args) {

        /**
         * Map 是一个接口，存储的数据形式是 Key-Value 形式的
         * HashMap 是 Map 接口的一个实现类
         * Key 值不可重复
         * Value 可以重复
         * 输出的数据格式是 {}
         */
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "3");
        map.put(2, "1");
        map.put(3, "2");
        map.put(4, "3");
        map.put(null, "5");

        System.out.println(map);

        /**
         * TreeMap 是 Map 接口的一个实现类，和 HashMap 的区别就是 Key 值不能为 null
         */
        Map<Integer, String> map1 = new TreeMap<>();
        map1.put(1, "1");
        map1.put(2, "2");
        map1.put(3, "3");

        System.out.println(map1);
    }
}
