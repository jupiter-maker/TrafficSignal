package com.jupiter.ts.controller;


import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.model.Intersection;
import com.jupiter.ts.service.IntersectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
/**
 * 路口Controller
 */
@RequestMapping("/intersection")
public class IntersectionController {

    @Autowired
    private IntersectionService intersectionService;

    @PostMapping("/checkIsName")
    @ResponseBody
    public TsResultDto checkIsName(@RequestParam("isName") String isName){
        boolean result = intersectionService.checkIsName(isName);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(300,"路口名重复");
    }

    @PostMapping("/save")
    @ResponseBody
    public TsResultDto saveIntersection(Intersection intersection){
        boolean result = intersectionService.saveIntersection(intersection);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(300,"添加路口失败");
    }

    /**
     * 获取路口信息列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public TsResultDto getIntersectionList(@RequestParam(value="pn",defaultValue="1")Integer pn){
        PageInfo pageInfo = intersectionService.getIntersectionList(pn, 10);
        return TsResultDto.ok(pageInfo);
    }

    @DeleteMapping("/delete/{ids}")
    @ResponseBody
    public TsResultDto deleteIntersection(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            boolean result = intersectionService.deleteIntersections(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(300,"批量删除失败");
            }
        }
        return TsResultDto.build(300,"单个删除失败");
    }
}
