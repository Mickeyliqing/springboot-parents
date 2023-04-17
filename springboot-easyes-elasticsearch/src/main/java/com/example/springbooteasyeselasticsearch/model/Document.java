package com.example.springbooteasyeselasticsearch.model;

import cn.easyes.annotation.HighLight;
import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.FieldType;
import cn.easyes.annotation.rely.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 指定索引名称
@IndexName("document")
public class Document implements Serializable {
    // 注解注解
    // IdType.NONE: 由ES自动生成,是默认缺省时的配置,无需您额外配置 推荐
    // IdType.UUID: 系统生成UUID,然后插入ES (不推荐)
    // IdType.CUSTOMIZE: 由用户自定义,用户自己对id值进行set,如果用户指定的id在es中不存在,则在insert时就会新增一条记录,如果用户指定的id在es中已存在记录,则自动更新该id对应的记录.
    @IndexId(type = IdType.NONE)
    private String id;

    // 字段注解，当我们需要对查询字段进行精确匹配,左模糊,右模糊,全模糊,排序聚合等操作时,需要该字段的索引类型为 keyword 类型
    @IndexField(fieldType = FieldType.KEYWORD)
    private String title;

    // 字段注解，当我们需要对字段进行分词查询时,需要该字段的类型为 text 类型,并且指定分词器(不指定就用 ES 默认分词器,效果通常不理想)
    @IndexField(fieldType = FieldType.TEXT)
    @HighLight
    private String content;

    // 字段注解，当同一个字段,我们既需要把它当 keyword 类型使用,又需要把它当 text 类型使用时,此时我们的索引类型为 keyword_text 类型
    // 当我们把该字段当做 keyword 类型查询时, ES 要求传入的字段名称为"字段名 .keyword",当把该字段当 text 类型查询时,直接使用原字段名即可
    // 如果一个字段的索引类型被创建为仅为 keyword 类型(如下图所示)查询时,则不需要在其名称后面追加 .keyword,直接查询就行
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String description;

}
