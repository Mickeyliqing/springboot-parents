package com.example.springbootmybatisplus.model.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * lq
 * </p>
 *
 * @author 李清
 * @since 2022-01-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DataArea extends Model<DataArea> {

    private static final long serialVersionUID = 1L;

    /**
     * 主表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主题域标识
     */
    private String areaMark;

    /**
     * 主题域名称
     */
    private String name;

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
