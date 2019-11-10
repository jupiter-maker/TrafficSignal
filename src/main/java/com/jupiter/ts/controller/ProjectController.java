package com.jupiter.ts.controller;

import com.github.pagehelper.PageInfo;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Project;
import com.jupiter.ts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //更新或保存方案id
    @PostMapping("/save")
    @ResponseBody
    public TsResultDto createOrUpdateProject(Project project){
        Project result = projectService.createOrUpdateProject(project);
        if(result.getId() == null){
            return TsResultDto.build(CustomizeErrorCode.PROJECT_CREATE_OR_UPDATE_FAILED);
        }
        return TsResultDto.ok(result);
    }

    //给方案设置主相位
    @PostMapping("/setZxw")
    @ResponseBody
    public TsResultDto setZxw(@RequestParam("faId") Integer faId,@RequestParam("faZxwId") Integer faZxwId){
        int result = projectService.setZxw(faId,faZxwId);
        if(result != 0){
            return TsResultDto.ok();
        }
        return TsResultDto.build(CustomizeErrorCode.ZXW_SET_FAILED);
    }

    @PostMapping("/list")
    @ResponseBody
    public TsResultDto getProjectsListByFaName(@RequestParam(value="pn",defaultValue="1")Integer pn,
                                               @RequestParam(value="search",required = false) String search){
        PageInfo pageInfo= projectService.getProjectsListByFaName(pn, 10, search);
        return TsResultDto.ok(pageInfo);
    }
}
