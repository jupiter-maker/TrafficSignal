package com.jupiter.ts.dto;

import lombok.Data;

@Data
public class IntersectionDto {
    private Integer id;
    private String isDdName;//所属大队
    private String isName;
    private String isDlName;
    private String isXhName; //设备型号
    private String isWhName;//维护人员姓名
    private Long isCreate;//创建时间

}
