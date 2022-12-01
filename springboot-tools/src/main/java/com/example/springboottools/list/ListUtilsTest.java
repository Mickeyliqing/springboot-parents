package com.example.springboottools.list;

import com.example.springboottools.model.PublicField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtilsTest {
    public static void main(String[] args) {
        // 引入 ListUtils 类
        ListUtils listUtils = new ListUtils();

        // 构造数据
        List<PublicField> publicFieldList = new ArrayList<PublicField>();
        PublicField publicField1 = new PublicField(1, "aa", "AA");
        PublicField publicField2 = new PublicField(2, "bb", "BB");
        PublicField publicField3 = new PublicField(3, "cc", "CC");
        publicFieldList.add(publicField1);
        publicFieldList.add(publicField2);
        publicFieldList.add(publicField3);

        // 构造条件数据
        List<String> nameList = Arrays.asList("AA", "BB", "cc");

        // 调用 List 的删除方法
        List list = listUtils.removeList(publicFieldList, nameList);
        System.out.println(list);
        System.out.println(publicFieldList.size());

        // 调用 List 的删除方法
        List listIf = listUtils.removeListIf(publicFieldList, nameList);
        System.out.println(listIf);
        System.out.println(publicFieldList.size());

        // 把 List 转成数据流处理里面的数据
        List<PublicField> collect = publicFieldList.stream().filter(publicField -> publicField.getName().equals("aa")).filter(publicField -> publicField.getType().equals("AA")).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(publicFieldList.size());
    }
}
