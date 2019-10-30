package com.jupiter.ts.service;

import com.jupiter.ts.mapper.AnnunciatorMapper;
import com.jupiter.ts.model.Annunciator;
import com.jupiter.ts.model.AnnunciatorExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信号机型号Service
 */
@Service
public class AnnunciatorService {

    @Autowired
    private AnnunciatorMapper annunciatorMapper;

    //返回所有型号机型号
    public List<Annunciator> getAllAnnunciator(){
        AnnunciatorExample annunciatorExample = new AnnunciatorExample();
        List<Annunciator> annunciators = annunciatorMapper.selectByExample(annunciatorExample);
        return annunciators;
    }
}
