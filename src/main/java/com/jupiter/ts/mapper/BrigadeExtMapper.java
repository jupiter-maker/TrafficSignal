package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.BrigadeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("brigadeExtMapper")
public interface BrigadeExtMapper {
    List<BrigadeDto> selectBrigadeList();
}
