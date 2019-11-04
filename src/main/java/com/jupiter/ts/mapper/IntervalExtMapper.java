package com.jupiter.ts.mapper;

import com.jupiter.ts.dto.IntervalDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("extIntervalMapper")
public interface IntervalExtMapper {

    List<IntervalDto> selectByIsId(@Param("id") Integer id);

    //返回指定路口id链表中配置了时段的路口数
    Integer selectIsNumByIsId(List<Integer> isIdList);
}
