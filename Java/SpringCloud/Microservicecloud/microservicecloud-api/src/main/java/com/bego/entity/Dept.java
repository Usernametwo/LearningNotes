package com.bego.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    //部门ID
    private Integer deptNo;

    //部门名称
    private String deptName;

    //来自哪个数据库
    private String dbSource;

}
