package com.jupiter.ts.dto;

import lombok.Data;

@Data
public class RoadStsDto {
    private Integer dlId;//道路id
    private Integer ddId;//大队id
    private String ddName;//大队名称
    private String dlName;//道路名称
    private Integer isNum;//路口数量
}
