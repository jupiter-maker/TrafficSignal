package com.jupiter.ts.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.mapper.AnnunciatorMapper;
import com.jupiter.ts.mapper.BrigadeMapper;
import com.jupiter.ts.mapper.IntersectionMapper;
import com.jupiter.ts.mapper.IntervalMapper;
import com.jupiter.ts.model.*;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private IntervalMapper intervalMapper;


    //根据id查询路口信息
    public Intersection getIntersectionInfoById(Integer id){
        Intersection intersection = intersectionMapper.selectByPrimaryKey(id);
            return intersection;
    }
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
    public boolean createOrUpdateIntersection(Intersection intersection){
        int i = 0;
        if(intersection.getId()==null){
            //添加路口
            intersection.setIsCreate(System.currentTimeMillis());
            intersection.setIsModified(System.currentTimeMillis());
            i = intersectionMapper.insertSelective(intersection);
        }else{
            intersection.setIsCreate(System.currentTimeMillis());
            intersection.setIsModified(System.currentTimeMillis());
            i = intersectionMapper.updateByPrimaryKeySelective(intersection);
        }
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