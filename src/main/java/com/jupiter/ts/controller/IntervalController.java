package com.jupiter.ts.controller;

import com.jupiter.ts.dto.IntervalDto;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Interval;
import com.jupiter.ts.service.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 路口时段管理Controller
 */
@Controller
@RequestMapping("/interval")
public class IntervalController {

    @Autowired
    private IntervalService intervalService;

    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createOrUpdateInterval(@RequestBody Interval interval){
        boolean result = intervalService.createOrUpdateInterval(interval);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.INTERVAL_CREATE_FAILED);
    }

    //根据路口id查询时段信息
    @GetMapping("/{isId}")
    @ResponseBody
    public TsResultDto selectByIsId(@PathVariable Integer isId){
        List<IntervalDto> intervalDtos = intervalService.selectByIsId(isId);
        if(intervalDtos.size() == 0 && intervalDtos == null){
            return TsResultDto.build(CustomizeErrorCode.INTERVAL_LIST_NOT_FOUND);
        }
        return TsResultDto.ok(intervalDtos);
    }
    //根据id删除时段
    @DeleteMapping("/{id}")
    @ResponseBody
    public TsResultDto deleteIntervalById(@PathVariable Integer id){
        boolean result = intervalService.deleteIntervalByid(id);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.INTERVAL_DELETE_FAILED);
    }
}
