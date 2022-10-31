package com.example.springboottools.list;
import com.example.springboottools.model.PublicField;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Iterator;
import java.util.List;

public class ListUtils {
    /**
     * 删除 List 内的满足条件的元素，并返回删除后的 List
     */
    public List removeList(List<PublicField> publicList, List<String> name) {
        // 先判断对应的两个 List 是否为空
        if (ObjectUtils.isNotEmpty(publicList) && ObjectUtils.isNotEmpty(name)) {
            // 调用 List 的迭代器方法
            Iterator<PublicField> iterator = publicList.iterator();
            while (iterator.hasNext()) {
                String publicName = iterator.next().getName();
                for (String str : name) {
                    if (str.equals(publicName)) {
                        iterator.remove();
                        // 这里删除后一定要跳出循环
                        break;
                    }
                }
            }
        }
        return publicList;
    }
}
