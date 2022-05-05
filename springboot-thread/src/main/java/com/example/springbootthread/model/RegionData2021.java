package com.example.springbootthread.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 全国区域编码数据表
 * </p>
 *
 * @author astupidcoder
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
// @Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("region_data_2021")
@Builder
public class RegionData2021 extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 区域编码
     */
    @ExcelProperty(value = "区域编码", index = 3)
    private String regionCode;

    /**
     * 区域名称
     */
    @ExcelProperty(value = "区域名称", index = 4)
    private String regionShortName;

    /**
     * 区域全称
     */
    @ExcelProperty(value = "区域全称", index = 5)
    private String regionFullName;

    /**
     * 区域级别
     */
    @ExcelProperty(value = "区域层级", index = 2)
    private Integer regionLevel;

    /**
     * 父级区域编码
     */
    @ExcelProperty(value = "父级区域编码", index = 1)
    private String parentRegionCode;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "操作时间", index = 9)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ExcelIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 区划编码历史字段
     */
    @ExcelProperty(value = "历史区划编码", index = 7)
    private String regionCodeHistory;

    /**
     * 区划来源
     */
    @ExcelProperty(value = "区划来源", index = 0)
    private String regionSource;

    /**
     * 区划别名
     */
    @ExcelProperty(value = "区域别名", index = 6)
    private String regionAlias;

    /**
     * 删除标识，0表示存在，1表示删除
     */
    @ExcelIgnore
    @JsonIgnore
    @TableLogic
    private Integer deleted;

    /**
     * 操作人
     */
    @ExcelProperty(value = "操作人", index = 8)
    private String operator;

    /**
     * 错误标识
     */
    @ExcelIgnore
    private Integer error;
    /**
     * 接收前端封装好的区划层级
     */
    @ExcelIgnore
    @TableField(exist = false)
    private List regionLevelList;

    /**
     * 表示前端的查询是否是模糊查询，0 是，1 否
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String blur;

    /**
     * 区划编码模糊查询使用字段
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String regionCodeBlur;

    /**
     * 区划编码精确查询使用的字段
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String regionCodePrecise;

    /**
     * 区划全称模糊查询使用的字段
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String regionSearchName;

    /**
     * 表明数据在 Excel 处于第几行
     */
    @ExcelIgnore
    @TableField(exist = false)
    private Integer line;

    /**
     * 查询的时候接收参数
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String startTime;

    /**
     * 查询的时候接收参数
     */
    @ExcelIgnore
    @TableField(exist = false)
    private String endTime;

    /**
     * 表示是否有下一层级，0无，1有
     */
    @ExcelIgnore
    @TableField(exist = false)
    private Integer nextLevel;

}
