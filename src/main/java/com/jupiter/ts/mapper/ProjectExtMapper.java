package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.ProjectDto;
import com.jupiter.ts.dto.ProjectsSts;
import com.jupiter.ts.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("projectExtMapper")
public interface ProjectExtMapper {
    //插入方案并返回方案主键
    Integer insertAndGetProjectId(Project project);
    //通过方案名检索方案列表
    List<ProjectDto>  getProjectsListByFaName(@Param("search") String search);
    //返回所有方案关联信息
    List<ProjectDto> getProjectsList();
    List<ProjectsSts> getProjectsSts();

}
