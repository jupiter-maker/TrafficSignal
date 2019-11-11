package com.jupiter.ts.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.RoadDto;
import com.jupiter.ts.dto.RoadStsDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.*;
import com.jupiter.ts.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadService {

    @Autowired
    private RoadMapper roadMapper;
    @Autowired
    private RoadExtMapper roadExtMapper;
    @Autowired
    private IntersectionMapper intersectionMapper;
    @Autowired
    private IntervalMapper intervalMapper;
    @Autowired
    private BrigadeMapper brigadeMapper;

    //根据大队id查询对应的道路
    public List<Road> getRoadListByDdId(Integer ddId){
        RoadExample roadExample = new RoadExample();
        roadExample.createCriteria().andDlDdIdEqualTo(ddId);
        List<Road> roads = roadMapper.selectByExample(roadExample);
        return roads;
    }

    //校验道路名是否可用
    public boolean checkDlName(String dlName){
        RoadExample roadExample = new RoadExample();
        roadExample.createCriteria().andDlNameEqualTo(dlName);
        List<Road> roads = roadMapper.selectByExample(roadExample);
        if(roads == null || roads.size() == 0){
            return true;
        }
        return false;
    }
    //根据道路名称获取道路信息分页列表
    public PageInfo getRoadList(Integer pn, int rows, String search){

        PageHelper.startPage(pn, rows);
        List<RoadDto> roads;
        try{
            if(StringUtils.isNotBlank(search)){
                //搜索框不为空
                roads = roadExtMapper.selectRoadListByDlName("%"+search+"%");
            }else{
                roads = roadExtMapper.selectRoadList();
            }
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.INTERSECTION_LIST_NOT_FOUND);
        }
        PageInfo page = new PageInfo(roads,rows);
        return page;
    }

    //更新或添加道路
    public boolean createOrUpdateRoad(Road road) {
        int i = 0;
        if(road.getId()==null){
            //添加路口
            road.setDlCreate(System.currentTimeMillis());
            road.setDlModified(System.currentTimeMillis());
            i = roadMapper.insertSelective(road);

        }else{
            //更新路口
            i = roadMapper.updateByPrimaryKeySelective(road);
        }
        if(i!=0){
            return true;
        }
        return false;
    }

    public Road getRoadById(Integer dlId) {
        Road road = roadMapper.selectByPrimaryKey(dlId);
        return road;
    }

    @Transactional
    public boolean deleteRoads(String ids) {
        String[] id_strs = ids.split("-");
        List<Integer> id_list = new ArrayList<>();
        for(String s:id_strs) {
            id_list.add(Integer.parseInt(s));
        }
        //删除道路信息
        RoadExample roadExample = new RoadExample();
        roadExample.createCriteria().andIdIn(id_list);
        roadMapper.deleteByExample(roadExample);

        //查询道路id
        //删除路口信息为次id的路口
        IntersectionExample example = new IntersectionExample();
        example.createCriteria().andIsDlIdIn(id_list);
        List<Intersection> intersections = intersectionMapper.selectByExample(example);
        //转换为路口id
        List<Integer> is_id_list = intersections.stream().map(intersection -> intersection.getId()).collect(Collectors.toList());
        if(is_id_list != null && is_id_list.size() > 0){
            //删除对应时段
            IntervalExample intervalExample = new IntervalExample();
            intervalExample.createCriteria().andSdIsIdIn(is_id_list);
            intervalMapper.deleteByExample(intervalExample);
        }
        //删除路口信息
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIsDlIdIn(id_list);
        int i = intersectionMapper.deleteByExample(intersectionExample);

        if(i >= 0 ){
            return true;
        }
        return false;
    }

    public boolean deleteRoad(String ids) {
        Integer id = Integer.parseInt(ids);

        int i = roadMapper.deleteByPrimaryKey(id);
        //查询道路id
        //删除路口信息为次id的路口
        IntersectionExample example = new IntersectionExample();
        example.createCriteria().andIsDlIdEqualTo(id);
        List<Intersection> intersections = intersectionMapper.selectByExample(example);
        //转换为路口id
        List<Integer> is_id_list = intersections.stream().map(intersection -> intersection.getId()).collect(Collectors.toList());
        if(is_id_list != null && is_id_list.size() > 0){
            //删除对应时段
            IntervalExample intervalExample = new IntervalExample();
            intervalExample.createCriteria().andSdIsIdIn(is_id_list);
            intervalMapper.deleteByExample(intervalExample);
        }
        //删除路口信息
        IntersectionExample intersectionExample = new IntersectionExample();
        intersectionExample.createCriteria().andIsDlIdEqualTo(id);
        intersectionMapper.deleteByExample(intersectionExample);

        if(i >= 0 ){
            return true;
        }
        return false;

    }

    //获取道路统计信息
    public List<RoadStsDto> selectRoadsSts() {
        List<RoadStsDto> roadStsDtos = roadExtMapper.selectRoadsSts();
        IntersectionExample intersectionExample;
        for(RoadStsDto r:roadStsDtos){
            //校正路口数
            if(r.getIsNum() == 1){
                intersectionExample = new IntersectionExample();
                intersectionExample.createCriteria().andIsDlIdEqualTo(r.getDlId());
                List<Intersection> intersections = intersectionMapper.selectByExample(intersectionExample);
                if(intersections == null || intersections.size() == 0){
                    r.setIsNum(0);
                }
            }
            //补全大队名称
            Brigade brigade = brigadeMapper.selectByPrimaryKey(r.getDdId());
            r.setDdName(brigade.getDdName());
        }
        return roadStsDtos;
    }
}
