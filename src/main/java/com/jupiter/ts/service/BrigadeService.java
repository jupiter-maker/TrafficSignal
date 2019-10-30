package com.jupiter.ts.service;

import com.jupiter.ts.mapper.BrigadeMapper;
import com.jupiter.ts.model.Brigade;
import com.jupiter.ts.model.BrigadeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大队信息管理Service
 */
@Service
public class BrigadeService {

    @Autowired
    private BrigadeMapper brigadeMapper;

    //返回所有大队名
    public List<Brigade> getAllBrigade(){
        BrigadeExample brigadeExample = new BrigadeExample();
        List<Brigade> brigades = brigadeMapper.selectByExample(brigadeExample);
        return brigades;
    }
}
