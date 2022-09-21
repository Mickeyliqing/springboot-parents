package com.example.springboottools.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
public class MapUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap();

        /**
         * 方式：一
         */
        for (String key : map.keySet()){
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        /**
         * 方式：二
         */
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        /**
         * 方式：三
         */
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        /**
         * 方式：四
         */
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

        /**
         * 方式：五
         */
        map.forEach((key, value) -> {

        });
    }
}
