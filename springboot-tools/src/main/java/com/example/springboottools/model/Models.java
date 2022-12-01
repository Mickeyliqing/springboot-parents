package com.example.springboottools.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Models implements Serializable {
    private Integer id;
    private Integer type;
    private Integer dateType;
}
