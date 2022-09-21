package com.example.springbootswagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户实体类")
public class UserParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sex;
}
