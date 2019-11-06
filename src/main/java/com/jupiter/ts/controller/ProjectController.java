package com.jupiter.ts.controller;

import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Project;
import com.jupiter.ts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 方案选择Controller
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("all")
    @ResponseBody
    public TsResultDto getAllProject(){
        List<Project> projects = projectService.getAllProject();
        if(projects == null && projects.size()==0){
            return TsResultDto.build(CustomizeErrorCode.PROJECT_NOT_FOUND);
        }
        return TsResultDto.ok(projects);
    }

    //通过方案Id获取方案信息
    @GetMapping("/{faId}")
    @ResponseBody
    public TsResultDto getProjectInfoById(@PathVariable("faId") Integer faId){
        Project project = projectService.getProjectInfoById(faId);
        if(project == null){
            return TsResultDto.build(CustomizeErrorCode.PROJECT_NOT_FOUND);
        }
        return TsResultDto.ok(project);
    }
}
