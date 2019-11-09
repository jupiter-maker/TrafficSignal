package com.jupiter.ts.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    ANNUNCIATOR_NOT_FOUND(2001,"信号机信息查询失败！"),
    BRIGADE_NOT_FOUND(2002,"大队信息查询失败！"),
    BRIGADE_LIST_NOT_FOUND(2003,"大队列表查询失败！"),
    SYS_ERROR(2004,"服务器冒烟了，请稍后再试！"),
    INTERSECTION_NOT_FOUND(2005,"路口可能已删除或不存在！"),
    INTERSECTION_BATCH_DELETE_FAILED(2006,"批量删除路口失败"),
    INTERSECTION_SINGLE_DELETE_FAILED(2007,"删除路口失败"),
    INTERSECTION_LIST_NOT_FOUND(2008,"路口列表查询失败"),
    INTERSECTION_CREATE_OR_UPDATE_FAILED(2009,"创建或更新路口失败！"),
    INTERSECTION_NAME_REPETITION(2010,"路口名重复！"),
    INTERVAL_CREATE_FAILED(2011,"创建时段失败！"),
    INTERVAL_LIST_NOT_FOUND(2012,"时段查询失败！"),
    INTERVAL_DELETE_FAILED(2013,"时段删除失败！"),
    PROJECT_NOT_FOUND(2014,"方案信息查询失败！"),
    ROAD_NOT_FOUND(2015,"道路信息查询失败！"),
    BRIGADE_NAME_REPETITION(2016,"大队名称重复！"),
    BRIGADE_CREATE_OR_UPDATE_FAILED(2017,"大队创建或更新失败！"),
    BRIGADE_DELETE_FAILED(2018,"大队删除失败！"),
    ROAD_NAME_REPETITION(2019,"道路名称重复！"),
    ROAD_CREATE_OR_UPDATE_FAILED(2020,"道路创建失败！"),
    PROJECT_CREATE_OR_UPDATE_FAILED(2021,"方案创建失败！"),
    PHASE_NOT_FOUND(2022,"相位信息为查询到！"),
    PHASE_CREATE_FAILED(2023,"相位创建失败!"),
    PHASE_DELETE_FAILED(2024,"相位删除失败！"),
    ZXW_SET_FAILED(2025,"主相位设置失败！")
    ;

    private Integer status;
    private String msg;

    CustomizeErrorCode(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getStatus() {
        return status;
    }
}
