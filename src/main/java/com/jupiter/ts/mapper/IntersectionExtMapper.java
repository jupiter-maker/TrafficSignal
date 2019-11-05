package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.IntersectionDto;
import com.jupiter.ts.model.Intersection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("intersectionExtMapper")
public interface IntersectionExtMapper {
    //返回所有路口
    List<IntersectionDto> selectIntersectionList();
    //根据路口名检索
    List<IntersectionDto> selectIntersectionListByIsName(@Param("search") String search);
    //根据大队id检索
    List<IntersectionDto> selectIntersectionListByDdId(@Param("search") Integer search);
}
