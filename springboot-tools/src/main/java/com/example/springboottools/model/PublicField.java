package com.example.springboottools.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublicField implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主表ID
     */
    private Integer id;

    /**
     * 字段标识
     */
    private String name;

    /**
     * 字段名称
     */
    private String chiName;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 长度
     */
    private Integer length;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除状态（默认为0表示存在）
     */
    private Integer deleted;
}
