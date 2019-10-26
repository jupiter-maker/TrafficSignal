package com.jupiter.ts.controller;

import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.mapper.IntersectionMapper;
import com.jupiter.ts.model.Intersection;
import com.jupiter.ts.model.IntersectionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
/**
 * 路口Controller
 */
@RequestMapping("/is")
public class IntersectionController {

    @Autowired
    private IntersectionMapper intersectionMapper;

    /**
     * 校验路口名是否重复
     * @param isName
     * @return
     */
    @PostMapping("/checkIsName")
    @ResponseBody
    public TsResultDto checkIsName(@RequestParam(name="isName") String isName){

        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIsNameEqualTo(isName);
        List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
        if(intersections.size()==0 || intersections==null){
            return TsResultDto.ok();
        }
        return TsResultDto.build(300,"路口名重复");
    }

    /**
     * 添加新路口
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public TsResultDto insertIntersection(Intersection intersection){
        int i = intersectionMapper.insertSelective(intersection);
        if(i==0){
            return TsResultDto.build(300,"添加失败");
        }
        return TsResultDto.ok();
    }
}
