package com.jupiter.ts.dto;

import lombok.Data;

@Data
public class ProjectDto {
    private Integer id;//方案id
    private Integer xwNum;//相位数
    private String faName;//方案名
    private String faMethod;//控制方式
    private String faZxwName;//主相位名
    private Integer faXwc;//相位差
    private Integer faZqcd;//周期长度
}
