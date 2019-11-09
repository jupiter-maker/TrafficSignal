package com.jupiter.ts.mapper;

import com.jupiter.ts.model.Project;
import org.springframework.stereotype.Component;

@Component("projectExtMapper")
public interface ProjectExtMapper {
    Integer insertAndGetProjectId(Project project);
}
