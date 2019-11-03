package com.jupiter.ts.controller;


import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.dto.IntersectionInfoDto;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.model.Intersection;
import com.jupiter.ts.service.IntersectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
/**
 * 路口Controller
 */
@RequestMapping("/intersection")
public class IntersectionController {

    @Autowired
    private IntersectionService intersectionService;


    /**
     * 根据id查询路口信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public TsResultDto getIntersectionInfoById(@PathVariable("id") Integer id){
        Intersection intersection = intersectionService.getIntersectionInfoById(id);
        if(intersection != null){
            return TsResultDto.ok(intersection);
        }
        return TsResultDto.build(300,"路口信息没有找到");

    }

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
    public TsResultDto createOrUpdateIntersection(Intersection intersection){
        boolean result = intersectionService.createOrUpdateIntersection(intersection);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(300,"添加或修改路口信息失败");
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

    /**
     * 删除路口信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    @ResponseBody
    public TsResultDto deleteIntersection(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            boolean result = intersectionService.deleteIntersections(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(300,"批量删除失败");
            }
        }else{
            //单个删除
            boolean result = intersectionService.deleteIntersection(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(300,"删除路口信息失败");
            }
        }
    }

    @RequestMapping("/info/{id}")
    public String intersectionInfo(@PathVariable("id") Integer id, Model model){
        Intersection intersection = intersectionService.getIntersectionInfoById(id);
        IntersectionInfoDto intersectionInfoDto = new IntersectionInfoDto();
        intersectionInfoDto.setId(intersection.getId());
        intersectionInfoDto.setModifiedTime(intersection.getIsModified());
        intersectionInfoDto.setWhName(intersection.getIsWhName());
        if(intersection != null){
            model.addAttribute("isInfo",intersectionInfoDto);
        }

        model.addAttribute("sectionId","intersectionInfo");
        model.addAttribute("sectionName","路口详情页");
        return "intersectionInfo";
    }
}
