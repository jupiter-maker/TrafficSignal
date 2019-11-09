package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.RoadDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("roadExtMapper")
public interface RoadExtMapper {

    List<RoadDto> selectRoadListByDlName(@Param("search") String search);
    List<RoadDto> selectRoadList();
}
