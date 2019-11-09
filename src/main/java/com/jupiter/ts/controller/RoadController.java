package com.jupiter.ts.controller;

import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Road;
import com.jupiter.ts.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/road")
public class RoadController {


    @Autowired
    private RoadService roadService;

    /**
     * 获取道路列表根据大队id
     * @param ddId
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public TsResultDto getRoadListByDdId(@RequestParam(value = "ddId",required = false) Integer ddId){
        List<Road> roads = roadService.getRoadListByDdId(ddId);
        if(roads == null && roads.size() == 0){
            return TsResultDto.build(CustomizeErrorCode.ROAD_NOT_FOUND);
        }
        return TsResultDto.ok(roads);
    }
    //校验道路名是否重复
    @PostMapping("/checkDlName")
    @ResponseBody
    public TsResultDto checkDlName(@RequestParam("dlName") String dlName){
        boolean result = roadService.checkDlName(dlName);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.ROAD_NAME_REPETITION);

    }

    @PostMapping("/list")
    @ResponseBody
    public TsResultDto getRoadListBySearch(@RequestParam(value="pn",defaultValue="1")Integer pn,
                                           @RequestParam(value="search",required = false) String search){
        PageInfo pageInfo = roadService.getRoadList(pn, 10, search);
        return TsResultDto.ok(pageInfo);
    }

    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createOrUpdateRoad(Road road){
        boolean result = roadService.createOrUpdateRoad(road);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.ROAD_CREATE_OR_UPDATE_FAILED);
    }

    //根据id返回道路信息
    @PostMapping("/{id}")
    @ResponseBody
    public TsResultDto getRoadById(@PathVariable("id") Integer dlId){
        Road road = roadService.getRoadById(dlId);
        if(road == null){
            return TsResultDto.build(CustomizeErrorCode.ROAD_NOT_FOUND);
        }
        return TsResultDto.ok(road);
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public TsResultDto deleteRoad(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            boolean result = roadService.deleteRoads(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(CustomizeErrorCode.INTERSECTION_BATCH_DELETE_FAILED);
            }
        }else{
            //单个删除
            boolean result = roadService.deleteRoad(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(CustomizeErrorCode.INTERSECTION_SINGLE_DELETE_FAILED);
            }
        }
    }
}