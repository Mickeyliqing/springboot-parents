package com.example.springbootvalidation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author tangpengjie
 * @className: QuartSearchCondition
 * @description: TODO(调度任务查询条件 实体类)
 * @date 2021年12月20日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartSearchCondition {

    /**
     * id
     */
    private Integer id;

    /**
     * 字段名
     */
    @NotEmpty(message = "name 不能为空")
    private String name;

    /**
     * 中文名
     */
    @NotEmpty(message = "chineseName 不能为空")
    private String chineseName;

    /**
     * 字段值，以json形式存储
     * 如：
     * ["green","garnett","nick","james","thompson","tom","cally","towns","lavin","wigins"]
     * {"gte":10,"lte":50}
     */
    @NotEmpty(message = "value 不能为空")
    private String value;

    /**
     * 查询方式：
     * 1  match  分词
     * 2  terms  多个完全匹配任一个
     * 3  term    完全匹配
     * 4  range  两者之间
     * 5  wildcard  模糊
     * 6  being 字段不为空
     */
    @NotNull(message = "conditionMode 不能为空")
    private Integer conditionMode;

    /**
     * 字段间关系 1 must  2 mustNot  3 should
     */
    @NotNull(message = "conditionType 不能为空")
    private Integer conditionType;

    /**
     * 报表id
     */
    private Integer jobId;
}
