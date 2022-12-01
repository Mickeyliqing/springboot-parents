package com.example.springboottools.tools;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilsTools {
    /**
     * CollectionUtils 常用 集合的操作，但这里引用的是 org.apache.commons.collections 下的
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        if (CollectionUtils.isNotEmpty(list)) {
            System.out.println(list);
        }
    }
}
