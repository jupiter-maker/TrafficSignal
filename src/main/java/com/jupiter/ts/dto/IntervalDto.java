package com.jupiter.ts.dto;

import lombok.Data;

@Data
public class IntervalDto {
    private Integer id; //时段id
    private Integer faId; //方案id
    private String sdStart;
    private String sdEnd;
    private String faName;
    private String faMethod;
    private String faZxw;
    private String faZxwName;
    private Integer faXwc; //相位差
    private Integer faZqcd;//周期长度
}
