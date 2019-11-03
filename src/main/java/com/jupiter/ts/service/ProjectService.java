package com.jupiter.ts.service;

import com.jupiter.ts.mapper.ProjectMapper;
import com.jupiter.ts.model.Project;
import com.jupiter.ts.model.ProjectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方案选择Service
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

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
}

