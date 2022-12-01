package com.example.springboottools.list;
import com.example.springboottools.model.Models;
import com.example.springboottools.model.PublicField;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtils {
    /**
     * 删除 List 内的满足条件的元素，并返回删除后的 List, 采取传统的写法
     */
    public List removeList(List<PublicField> publicList, List<String> nameList) {
        // 调用 List 的迭代器方法
        Iterator<PublicField> iterator = publicList.iterator();
        while (iterator.hasNext()) {
            String publicName = iterator.next().getName();
            for (String str : nameList) {
                if (str.equals(publicName)) {
                    iterator.remove();
                    // 这里删除后一定要跳出循环
                    break;
                }
            }
        }
        return publicList;
    }

    /**
     * 删除 List 内的满足条件的元素，并返回删除后的 List，直接调用 List 内的 removeIf 方法
     */
    public List removeListIf(List<PublicField> publicList, List<String> nameList) {
       publicList.removeIf(publicField -> {
           nameList.forEach(items -> items.equals(publicField.getName()));
           return false;
       });
       return publicList;
    }

    public static void main(String[] args) {
        Models models1 = new Models(1, 2, 6);
        Models models2 = new Models(2, 3, 7);
        Models models3 = new Models(3, 4, 8);
        Models models4 = new Models(4, 4, 8);
        List<Models> list = new ArrayList<>();
        list.add(models1);
        list.add(models2);
        list.add(models3);
        list.add(models4);
        // [Models(id=1, type=2, dateType=6), Models(id=2, type=3, dateType=7), Models(id=3, type=4, dateType=8), Models(id=4, type=5, dateType=8)]

        // 根据 dateType 的值进行分组
        //{6=[Models(id=1, type=2, dateType=6)], 7=[Models(id=2, type=3, dateType=7)], 8=[Models(id=3, type=4, dateType=8), Models(id=4, type=5, dateType=8)]}
        Map<Integer, List<Models>> collect1 = list.stream().collect(Collectors.groupingBy(Models::getDateType));

        // 根据 dateType 值转成 Map 这个就能过滤掉 dateType 值存在重复数据的情况
        //{6=Models(id=1, type=2, dateType=6), 7=Models(id=2, type=3, dateType=7), 8=Models(id=3, type=4, dateType=8)}
        Map<Integer, Models> collect2 = list.stream().collect(Collectors.toMap(Models::getDateType, Function.identity(), (i1, i2) -> i1));

        // collect2 转成 List
        //[Models(id=1, type=2, dateType=6), Models(id=2, type=3, dateType=7), Models(id=3, type=4, dateType=8)]
        Collection<Models> listValues = list.stream().collect(Collectors.toMap(Models::getDateType, Function.identity(), (i1, i2) -> i1)).values();

        // 直接过滤掉 dateType 值存在重复数据的情况
        // [Models(id=1, type=2, dateType=6), Models(id=2, type=3, dateType=7), Models(id=3, type=4, dateType=8)]
        ArrayList<Models> collect3 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Models::getDateType))), ArrayList::new));

        // 过滤掉 dateType 和 type 的值存在重复数据的情况
        // [Models(id=1, type=2, dateType=6), Models(id=2, type=3, dateType=7), Models(id=3, type=4, dateType=8)]
        ArrayList<Models> collect4 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(items -> items.getDateType() + ";" + items.getType()))), ArrayList::new));

    }
}
