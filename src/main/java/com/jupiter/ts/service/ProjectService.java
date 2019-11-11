package com.jupiter.ts.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.dto.ProjectDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.IntervalMapper;
import com.jupiter.ts.mapper.PhaseMapper;
import com.jupiter.ts.mapper.ProjectExtMapper;
import com.jupiter.ts.mapper.ProjectMapper;
import com.jupiter.ts.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 方案选择Service
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectExtMapper projectExtMapper;
    @Autowired
    private PhaseMapper phaseMapper;
    @Autowired
    private IntervalMapper intervalMapper;

    //获取所有方方案信息
    public List<Project> getAllProject(){
        ProjectExample projectExample = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(projectExample);
        return projects;
    }

    //通过方案Id获取方案信息
    public Project getProjectInfoById(Integer faId){
        Project project = projectMapper.selectByPrimaryKey(faId);
        return project;
    }

    //创建或更新方案信息
    public Project createOrUpdateProject(Project project) {
        if(project.getId() == null){
            //创建方案
            project.setFaCreate(System.currentTimeMillis());
            project.setFaModified(System.currentTimeMillis());
            projectExtMapper.insertAndGetProjectId(project);
        }else{
            //更新方案
            project.setFaModified(System.currentTimeMillis());
            projectMapper.updateByPrimaryKeySelective(project);
        }
        return project;
    }

    public int setZxw(Integer faId, Integer faZxwId) {
        Phase phase = phaseMapper.selectByPrimaryKey(faZxwId);
        Project project = new Project();
        project.setId(faId);
        if(phase != null){
           project.setFaZxw(phase.getId());
           project.setFaZxwName(phase.getXwName());
           project.setFaModified(System.currentTimeMillis());
        }
        int i = projectMapper.updateByPrimaryKeySelective(project);
        return i;
    }

    //分页检索
    public PageInfo getProjectsListByFaName(Integer pn, int rows, String search) {
        PageHelper.startPage(pn,rows);
        List<ProjectDto> projectDtos;
        try{
            if(StringUtils.isNotBlank(search)){
                //搜索框不为空
                projectDtos = projectExtMapper.getProjectsListByFaName("%"+search+"%");
            }else{
                projectDtos = projectExtMapper.getProjectsList();
            }
        }catch(Exception e){
            throw new CustomizeException(CustomizeErrorCode.PROJECT_NOT_FOUND);
        }
        PageInfo page = new PageInfo(projectDtos,rows);
        return page;
    }

    //根据方案id删除方案
    @Transactional
    public int deleteProjectById(Integer id) {
        int i;
        try{
           //删除属于该相位的方案
           PhaseExample phaseExample = new PhaseExample();
           phaseExample.createCriteria().andXwFaIdEqualTo(id);
           phaseMapper.deleteByExample(phaseExample);
           //删除属于该方案的时段
           IntervalExample intervalExample = new IntervalExample();
           intervalExample.createCriteria().andSdFaIdEqualTo(id);
           intervalMapper.deleteByExample(intervalExample);
           //删除方案
           i = projectMapper.deleteByPrimaryKey(id);

       }catch(Exception e){
           throw new CustomizeException(CustomizeErrorCode.PROJECT_DELETE_FAILED);
       }
        return i;
    }
}

