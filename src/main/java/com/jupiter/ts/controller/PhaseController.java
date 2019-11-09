package com.jupiter.ts.controller;

import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Phase;
import com.jupiter.ts.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 相位Controller
 */
@Controller
@RequestMapping("/phase")
public class PhaseController {

    @Autowired
    private PhaseService phaseService;

    @GetMapping("/{xwId}")
    @ResponseBody
    public TsResultDto getPhaseById(@PathVariable("xwId") Integer xwId){
        Phase phase = phaseService.getPhaseById(xwId);
        return TsResultDto.ok(phase);
    }


    //根据方案id获取相位列表
    @GetMapping("/list")
    @ResponseBody
    public TsResultDto getPhaseListByFaId(@RequestParam("faId") Integer faId){
        List<Phase> result = phaseService.getPhaseListByFaId(faId);
        if(result == null && result.size() == 0){
            return TsResultDto.build(CustomizeErrorCode.PHASE_NOT_FOUND);
        }
        return TsResultDto.ok(result);
    }

    //添加相位
    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createPhase(@RequestBody Phase phase){
        int result = phaseService.createPhase(phase);
        if(result != 0){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.PHASE_CREATE_FAILED);
    }
    //删除相位
    @DeleteMapping("/{id}")
    @ResponseBody
    public TsResultDto deletePhase(@PathVariable("id") Integer id){
        int result = phaseService.deletePhaseById(id);
        if(result != 0){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.PHASE_DELETE_FAILED);
    }
}
