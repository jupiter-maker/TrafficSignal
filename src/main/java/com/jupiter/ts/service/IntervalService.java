package com.jupiter.ts.service;

import com.jupiter.ts.dto.IntervalDto;
import com.jupiter.ts.mapper.IntervalExtMapper;
import com.jupiter.ts.mapper.IntervalMapper;
import com.jupiter.ts.model.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 路口管理Service
 */
@Service
public class IntervalService {

    @Autowired
    private IntervalMapper intervalMapper;
    @Autowired
    private IntervalExtMapper intervalExtMapper;
    //创建时段
    public boolean createOrUpdateInterval(Interval interval) {
        interval.setSdName("时间段");
        interval.setSdCreate(System.currentTimeMillis());
        interval.setSdModified(System.currentTimeMillis());
        int i = intervalMapper.insertSelective(interval);
        if(i != 0){
            return true;
        }
        return false;
    }

    //根据isId查询时段
    public List<IntervalDto> selectByIsId(Integer isId){
        List<IntervalDto> intervalDtos = intervalExtMapper.selectByIsId(isId);
        return intervalDtos;
    }

    //根据id删除时段
    public boolean deleteIntervalByid(Integer id) {
        int i = intervalMapper.deleteByPrimaryKey(id);
        if(i != 0){
            return true;
        }
        return false;
    }
}
