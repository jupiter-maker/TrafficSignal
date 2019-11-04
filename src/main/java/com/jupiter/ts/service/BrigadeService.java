package com.jupiter.ts.service;

import com.jupiter.ts.dto.BrigadeDto;
import com.jupiter.ts.mapper.BrigadeExtMapper;
import com.jupiter.ts.mapper.BrigadeMapper;
import com.jupiter.ts.mapper.IntersectionMapper;
import com.jupiter.ts.mapper.IntervalExtMapper;
import com.jupiter.ts.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 大队信息管理Service
 */
@Service
public class BrigadeService {

    @Autowired
    private BrigadeMapper brigadeMapper;
    @Autowired
    private BrigadeExtMapper brigadeExtMapper;
    @Autowired
    private IntervalExtMapper intervalExtMapper;
    @Autowired
    private IntersectionMapper intersectionMapper;

    //返回所有大队名
    public List<Brigade> getAllBrigade(){
        BrigadeExample brigadeExample = new BrigadeExample();
        List<Brigade> brigades = brigadeMapper.selectByExample(brigadeExample);
        return brigades;
    }

    //返回所有大队统计信息
    public List<BrigadeDto> getBrigadeList(){
        List<BrigadeDto> brigadeDtos = brigadeExtMapper.selectBrigadeList();
        IntersectionExample intersectionExample;
        for(BrigadeDto bd:brigadeDtos){
            //查找大队id对应的所有路口id
            intersectionExample = new IntersectionExample();
            intersectionExample.createCriteria().andIsDdIdEqualTo(bd.getId());
            List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
            if(intersections != null && intersections.size() !=0 ){
                //将查询出来的路口转为对应的路口id链表
                List<Integer> intersectionIds = intersections.stream().map(intersection -> intersection.getId()).collect(Collectors.toList());
                Integer isSdNum = intervalExtMapper.selectIsNumByIsId(intersectionIds);
                bd.setIsSdNum(isSdNum);
                bd.setIsFaNum(isSdNum);
            }else{
                bd.setIsNum(0);
                bd.setIsSdNum(0);
                bd.setIsFaNum(0);
            }

        }

        return brigadeDtos;
    }
}
