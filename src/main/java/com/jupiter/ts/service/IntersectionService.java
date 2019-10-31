package com.jupiter.ts.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.mapper.AnnunciatorMapper;
import com.jupiter.ts.mapper.BrigadeMapper;
import com.jupiter.ts.mapper.IntersectionMapper;
import com.jupiter.ts.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 路口信息管理Service
 */
@Service
public class IntersectionService {

    @Autowired
    private IntersectionMapper intersectionMapper;
    @Autowired
    private BrigadeMapper brigadeMapper;
    @Autowired
    private AnnunciatorMapper annunciatorMapper;

    //校验路口名
    public boolean checkIsName(String isName){
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIsNameEqualTo(isName);
        List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
        if(intersections==null || intersections.size()==0){
            return true;
        }
        return false;
    }

    //保存路口
    public boolean saveIntersection(Intersection intersection){
        intersection.setIsCreate(System.currentTimeMillis());
        intersection.setIsModified(System.currentTimeMillis());
        int i = intersectionMapper.insertSelective(intersection);
        if(i != 0){
            return true;
        }
        return false;
    }

    //获取路口信息分页列表
    public PageInfo getIntersectionList(Integer pn,int rows){
        //获取大队信息
        BrigadeExample example = new BrigadeExample();
        List<Brigade> brigades = brigadeMapper.selectByExample(example);
        //将大队信息转换为Map集合
        Map<Integer,String> brigadeMap = brigades.stream().collect(Collectors.toMap(brigade -> brigade.getId(),brigade -> brigade.getDdName()));

        //获取设备型号信息
        AnnunciatorExample annunciatorExample = new AnnunciatorExample();
        List<Annunciator> annunciators = annunciatorMapper.selectByExample(annunciatorExample);
        Map<Integer,String> annunciatorMap = annunciators.stream().collect(Collectors.toMap(annunciator -> annunciator.getId(),annunciator -> annunciator.getXhName()));

        PageHelper.startPage(pn, rows);
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.setOrderByClause("is_dd_id");
        List<Intersection> intersections =  intersectionMapper.selectByExample(intersectionExample);
        //转换intersection为intersectionDto
        List<IntersectionDto> intersectionDtos = intersections.stream().map(intersection -> {
            IntersectionDto intersectionDto = new IntersectionDto();
            BeanUtils.copyProperties(intersection,intersectionDto);
            intersectionDto.setIsDdName(brigadeMap.get(intersection.getIsDdId()));
            intersectionDto.setIsXhName(annunciatorMap.get(intersection.getIsXhId()));
            return intersectionDto;
        }).collect(Collectors.toList());
        PageInfo page = new PageInfo(intersectionDtos,rows);
        return page;
    }

    //批量删除路口信息
    public boolean deleteIntersections(String ids){
        String[] id_strs = ids.split("-");
        List<Integer> id_list = new ArrayList<>();
        for(String s:id_strs) {
            id_list.add(Integer.parseInt(s));
        }
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIdIn(id_list);
        int i = intersectionMapper.deleteByExample(intersectionExample);
        if(i >= 0 ){
            return true;
        }
        return false;
    }
}