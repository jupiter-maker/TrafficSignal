package com.jupiter.ts.controller;


import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.IntersectionMapper;
import com.jupiter.ts.model.Intersection;
import com.jupiter.ts.service.IntersectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@Controller
/**
 * 路口Controller
 */
@RequestMapping("/intersection")
public class IntersectionController {

    @Autowired
    private IntersectionService intersectionService;
    @Autowired
    private IntersectionMapper intersectionMapper;


    /**
     * 根据id查询路口信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public TsResultDto getIntersectionByIsId(@PathVariable("id") Integer id){
        Intersection intersection = intersectionService.getIntersectionInfoById(id);
        if(intersection != null){
            return TsResultDto.ok(intersection);
        }
        return TsResultDto.build(CustomizeErrorCode.INTERSECTION_NOT_FOUND);

    }

    @PostMapping("/checkIsName")
    @ResponseBody
    public TsResultDto checkIsName(@RequestParam("isName") String isName){
        boolean result = intersectionService.checkIsName(isName);
        if(result){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.INTERSECTION_NAME_REPETITION);
    }

    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createIntersection(Intersection intersection){
        int result = intersectionService.createOrUpdateIntersection(intersection);
        if(result != 0 ){
            return TsResultDto.ok(result);
        }
        return TsResultDto.build(CustomizeErrorCode.INTERSECTION_CREATE_OR_UPDATE_FAILED);
    }

    /**
     * 通过路口名检索获取路口信息列表
     * @return
     */
    @PostMapping("/list/is")
    @ResponseBody
    public TsResultDto getIntersectionListByIs(@RequestParam(value="pn",defaultValue="1")Integer pn,
                                           @RequestParam(value="search",required = false) String search){
        //System.out.println("/list/is"+search);
        PageInfo pageInfo = intersectionService.getIntersectionListByIsName(pn, 10, search);
        return TsResultDto.ok(pageInfo);
    }

    /**
     * 通过大队id检索获取路口信息列表
     * @return
     */
    @PostMapping("/list/brigade")
    @ResponseBody
    public TsResultDto getIntersectionListByBrigade(@RequestParam(value="pn",defaultValue="1")Integer pn,
                                           @RequestParam(value="search",required = false) Integer search){
        //System.out.println("/list/brigade"+search);
        PageInfo pageInfo = intersectionService.getIntersectionListByBrigade(pn, 10,search);
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
                return TsResultDto.build(CustomizeErrorCode.INTERSECTION_BATCH_DELETE_FAILED);
            }
        }else{
            //单个删除
            boolean result = intersectionService.deleteIntersection(ids);
            if(result){
                return TsResultDto.ok();
            }else{
                return TsResultDto.build(CustomizeErrorCode.INTERSECTION_SINGLE_DELETE_FAILED);
            }
        }
    }

    //根据路口获取路口详细信息
    @GetMapping("/relInfo/{id}")
    @ResponseBody
    public TsResultDto getIntersectionInfoById(@PathVariable("id") Integer isId){
        IntersectionDto intersectionDto = intersectionService.getIntersectionRelInfoById(isId);
        if(intersectionDto == null){
            return TsResultDto.build(CustomizeErrorCode.INTERSECTION_NOT_FOUND);
        }
        return TsResultDto.ok(intersectionDto);
    }

    //跳转到路口详情页
    @RequestMapping("/info/{id}")
    public String intersectionInfo(@PathVariable("id") Integer id, Model model){
        Intersection intersection = intersectionService.getIntersectionInfoById(id);
        if(intersection != null){
            model.addAttribute("isInfo",intersection);
        }else{
            throw new CustomizeException(CustomizeErrorCode.INTERSECTION_NOT_FOUND);
        }

        model.addAttribute("sectionId","intersectionInfo");
        model.addAttribute("sectionName","路口详情页");
        return "intersectionInfo";
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public TsResultDto addTestIs(){
//        Intersection intersection = new Intersection();
//        Random random = new Random();
//        for(int i = 0;i<15;i++){
//            //添加第六大队路口
//            intersection.setIsDdId(6);//第六大队
//            intersection.setIsDlId(16);//第六大队随机道路
//            intersection.setIsXhId(random.nextInt(6)+1);
//            //随机道路名
//            intersection.setIsName("测试道路"+ UUID.randomUUID().toString().substring(0,3)+random.nextInt(100));
//            intersection.setIsWhName("维护人员测试"+ UUID.randomUUID().toString().substring(0,2));
//            intersection.setIsCreate(System.currentTimeMillis());
//            intersection.setIsModified(System.currentTimeMillis());
//            intersection.setIsXwNum(6);
//            intersectionMapper.insertSelective(intersection);
//        }
//        return TsResultDto.ok();
//    }
}
