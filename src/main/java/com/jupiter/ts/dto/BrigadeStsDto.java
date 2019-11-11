package com.jupiter.ts.dto;

import lombok.Data;

@Data
//大队统计dto
public class BrigadeStsDto {
    private Integer ddId;//大队id
    private String ddName;//大队名
    private Integer dlNum;//道路Num
    private Integer isNum;//路口数
}
