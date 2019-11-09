package com.jupiter.ts.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.*;
import com.jupiter.ts.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private IntersectionExtMapper intersectionExtMapper;
    @Autowired
    private IntervalMapper intervalMapper;


    //根据id查询路口信息
    public Intersection getIntersectionInfoById(Integer id){
        Intersection intersection =null;
        try{
            intersection = intersectionMapper.selectByPrimaryKey(id);
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.INTERSECTION_NOT_FOUND);
        }
        return intersection;
    }

    //根据路口id查询路口关联信息
    public IntersectionDto getIntersectionRelInfoById(Integer isId){
        IntersectionDto intersectionDto = intersectionExtMapper.selectIntersectionRelInfoByIsId(isId);
        return intersectionDto;
    }
    //校验路口名
    public boolean checkIsName(String isName){
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIsNameEqualTo(isName);
        List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
        if(intersections == null || intersections.size()==0){
            return true;
        }
        return false;
    }

    //保存路口
    public int createOrUpdateIntersection(Intersection intersection){
        int i = 0;
        if(intersection.getId()==null){
            //添加路口
            intersection.setIsCreate(System.currentTimeMillis());
            intersection.setIsModified(System.currentTimeMillis());
            intersectionExtMapper.insertAndGetIsId(intersection);
            //System.out.println(intersection.getId());
            i = intersection.getId();
        }else{
            //更新路口
            i = intersectionMapper.updateByPrimaryKeySelective(intersection);
        }
        return i;
    }

    //根据路口名称获取路口信息分页列表
    public PageInfo getIntersectionListByIsName(Integer pn, int rows, String search){

        PageHelper.startPage(pn, rows);
        List<IntersectionDto> intersectionDtos;
        try{
            if(StringUtils.isNotBlank(search)){
                //搜索框不为空
                intersectionDtos = intersectionExtMapper.selectIntersectionListByIsName("%"+search+"%");
            }else{
                intersectionDtos = intersectionExtMapper.selectIntersectionList();
            }
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.INTERSECTION_LIST_NOT_FOUND);
        }
        PageInfo page = new PageInfo(intersectionDtos,rows);
        return page;
    }

    //根据大队id获取路口信息分页列表
    public PageInfo getIntersectionListByBrigade(Integer pn,int rows,Integer search){

        PageHelper.startPage(pn, rows);
        List<IntersectionDto> intersectionDtos;
        try{
            if(search != null){
                //大队id不为空
                intersectionDtos = intersectionExtMapper.selectIntersectionListByDdId(search);
            }else{
                intersectionDtos = intersectionExtMapper.selectIntersectionList();
            }
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.INTERSECTION_LIST_NOT_FOUND);
        }
        PageInfo page = new PageInfo(intersectionDtos,rows);
        return page;
    }
    //单个删除路口信息
    public boolean deleteIntersection(String ids){
        Integer id = Integer.parseInt(ids);
        //删除路口信息
        int i = intersectionMapper.deleteByPrimaryKey(id);
        //删除对应的时段信息
        IntervalExample intervalExample = new IntervalExample();
        intervalExample.createCriteria().andSdIsIdEqualTo(id);
        intervalMapper.deleteByExample(intervalExample);
        if(i != 0){
            return true;
        }
        return false;
    }
    //批量删除路口信息
    @Transactional
    public boolean deleteIntersections(String ids){
        String[] id_strs = ids.split("-");
        List<Integer> id_list = new ArrayList<>();
        for(String s:id_strs) {
            id_list.add(Integer.parseInt(s));
        }
        //删除路口信息
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIdIn(id_list);
        int i = intersectionMapper.deleteByExample(intersectionExample);
        //删除对应时段信息
        IntervalExample intervalExample = new IntervalExample();
        intervalExample.createCriteria().andSdIsIdIn(id_list);
        intervalMapper.deleteByExample(intervalExample);
        if(i >= 0 ){
            return true;
        }
        return false;
    }
}