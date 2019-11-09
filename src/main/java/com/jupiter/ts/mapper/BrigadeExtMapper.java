package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.BrigadeDto;
import com.jupiter.ts.model.BrigadeSts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("brigadeExtMapper")
public interface BrigadeExtMapper {
    List<BrigadeDto> selectBrigadeList();
    List<BrigadeSts> selectBrigadeSts(@Param("ddId") Integer ddId);
}
