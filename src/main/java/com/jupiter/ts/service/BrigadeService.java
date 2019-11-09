package com.jupiter.ts.service;

import com.jupiter.ts.dto.BrigadeDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.*;
import com.jupiter.ts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private IntervalMapper intervalMapper;
    @Autowired
    private IntersectionMapper intersectionMapper;
    @Autowired
    private RoadMapper roadMapper;


    //删除大队根据大队id
    @Transactional
    public boolean deleteBrigadeById(Integer ddId){
        try{

            brigadeMapper.deleteByPrimaryKey(ddId);
            //删除对应的道路表，路口表，时段表
            RoadExample roadExample = new RoadExample();
            roadExample.createCriteria().andDlDdIdEqualTo(ddId);
            roadMapper.deleteByExample(roadExample);

            IntersectionExample intersectionExample = new IntersectionExample();
            intersectionExample.createCriteria().andIsDdIdEqualTo(ddId);
            List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
            //将查询出来的路口转换为路口id链表
            List<Integer> isIdList = intersections.stream().map(intersection -> intersection.getId()).collect(Collectors.toList());
            //删除所有相关路口
            IntersectionExample example = new IntersectionExample();
            example.createCriteria().andIsDdIdEqualTo(ddId);
            intersectionMapper.deleteByExample(example);
            if(isIdList != null && isIdList.size() != 0){
                //删除相关路口对应的所有时段
                IntervalExample intervalExample = new IntervalExample();
                intervalExample.createCriteria().andSdIsIdIn(isIdList);
                intervalMapper.deleteByExample(intervalExample);
            }
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.BRIGADE_DELETE_FAILED);
        }

        return true;
    }
    //根据大队id返回大队信息
    public Brigade getBrigade(Integer ddId){
        Brigade brigade = brigadeMapper.selectByPrimaryKey(ddId);
        return brigade;
    }
    //返回所有大队名
    public List<Brigade> getAllBrigade(){
        BrigadeExample brigadeExample = new BrigadeExample();
        List<Brigade> brigades = brigadeMapper.selectByExample(brigadeExample);
        return brigades;
    }

    //创建或更新大队信息
    public boolean createOrUpdateBrigade(Brigade brigade){
        int i = 0;
        if(brigade.getId() == null){
            //创建大队
            brigade.setDdCreate(System.currentTimeMillis());
            brigade.setDdModified(System.currentTimeMillis());
            i = brigadeMapper.insertSelective(brigade);
        }else{
            brigade.setDdModified(System.currentTimeMillis());
            i = brigadeMapper.updateByPrimaryKeySelective(brigade);
        }
        if(i != 0){
            return true;
        }
        return false;
    }
    //返回所有大队统计信息
    public List<BrigadeDto> getBrigadeList(){
        List<BrigadeDto> brigadeDtos = null;
        try{
            brigadeDtos = brigadeExtMapper.selectBrigadeList();
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
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.BRIGADE_LIST_NOT_FOUND);
        }
        return brigadeDtos;
    }

    //校验大队名是否重复
    public boolean checkDdName(String ddName){
        BrigadeExample brigadeExample = new BrigadeExample();
        brigadeExample.createCriteria().andDdNameEqualTo(ddName);
        List<Brigade> brigades = brigadeMapper.selectByExample(brigadeExample);
        if(brigades == null || brigades.size() == 0){
            return true;
        }
        return false;
    }
}
