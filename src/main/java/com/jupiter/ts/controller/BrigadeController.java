package com.jupiter.ts.controller;

import com.jupiter.ts.dto.BrigadeDto;
import com.jupiter.ts.dto.BrigadeStsDto;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Brigade;
import com.jupiter.ts.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 大队信息管理Controller
 */
@Controller
@RequestMapping("/brigade")
public class BrigadeController {

    @Autowired
    private BrigadeService brigadeService;


    @GetMapping("/{ddId}")
    @ResponseBody
    public TsResultDto getBrigade(@PathVariable("ddId") Integer ddId){
        Brigade brigade = brigadeService.getBrigade(ddId);
        if(brigade != null){
            return TsResultDto.ok(brigade);
        }
        return TsResultDto.build(CustomizeErrorCode.BRIGADE_NOT_FOUND);
    }
    @DeleteMapping("/{ddId}")
    @ResponseBody
    public TsResultDto deleteBrigadeById(@PathVariable() Integer ddId){

        boolean result = brigadeService.deleteBrigadeById(ddId);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.BRIGADE_DELETE_FAILED);
    }
    /**
     * 返回所有大队信息
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public TsResultDto getAllBrigade(){
        List<Brigade> brigades = brigadeService.getAllBrigade();
        if(brigades == null || brigades.size() == 0){
            return TsResultDto.build(CustomizeErrorCode.BRIGADE_NOT_FOUND);
        }
        return TsResultDto.ok(brigades);
    }

    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createOrUpdateBrigade(Brigade brigade){
        boolean result = brigadeService.createOrUpdateBrigade(brigade);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.BRIGADE_CREATE_OR_UPDATE_FAILED);
    }


    @PostMapping("/checkDdName")
    @ResponseBody
    public TsResultDto checkDdName(@RequestParam("ddName") String ddName){
        boolean result = brigadeService.checkDdName(ddName);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.BRIGADE_NAME_REPETITION);

    }
    @GetMapping("/intersections")
    public String brigadeIntersection(@RequestParam(value="brigadeId",defaultValue = "1") Integer brigadeId, Model model){
        model.addAttribute("brigadeId",brigadeId);
        model.addAttribute("sectionId","brigadeIntersection");
        model.addAttribute("sectionName","大队-路口信息统计");
        return "intersectionsEdit";
    }

    /**
     * 大队-道路 大队-路口 统计信息
     * @return
     */
    @GetMapping("/sts")
    @ResponseBody
    public TsResultDto getBrigadesSts(){
        List<BrigadeStsDto> result = brigadeService.selectBrigadeSts();
        return TsResultDto.ok(result);
    }
    /**
     * 返回大队-路口详细统计 信息
     * @return
     */
    @GetMapping("/list/sts")
    @ResponseBody
    public TsResultDto getBrigadeList(){
        List<BrigadeDto> brigadeList = brigadeService.getBrigadeList();
        return TsResultDto.ok(brigadeList);
    }
}
