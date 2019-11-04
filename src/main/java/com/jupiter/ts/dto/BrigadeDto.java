package com.jupiter.ts.dto;

import lombok.Data;

@Data
public class BrigadeDto {
    private Integer id;       //大队Id
    private String ddName;    //大队名称
    private Integer isNum;    //路口数
    private Integer isSdNum;  //配置时段路口数
    private Integer isFaNum;  //配置方案路口数
    private Integer isSelf;   //单位自建路口数

}
