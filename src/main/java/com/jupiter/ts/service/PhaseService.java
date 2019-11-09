package com.jupiter.ts.service;

import com.jupiter.ts.mapper.PhaseMapper;
import com.jupiter.ts.model.Phase;
import com.jupiter.ts.model.PhaseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhaseService {

    @Autowired
    private PhaseMapper phaseMapper;


    //根据相位id获取相位信息
    public Phase getPhaseById(Integer xwId) {
        Phase phase = phaseMapper.selectByPrimaryKey(xwId);
        return phase;
    }

    //根据方案id获取方案id
    public List<Phase> getPhaseListByFaId(Integer faId) {
        PhaseExample phaseExample = new PhaseExample();
        phaseExample.createCriteria().andXwFaIdEqualTo(faId);
        List<Phase> phases = phaseMapper.selectByExample(phaseExample);
        return phases;
    }

    //创建相位
    public int createPhase(Phase phase) {
        phase.setXwCreate(System.currentTimeMillis());
        phase.setXwModofied(System.currentTimeMillis());
        int i = phaseMapper.insertSelective(phase);
        return i;
    }

    public int deletePhaseById(Integer id) {
        int i = phaseMapper.deleteByPrimaryKey(id);
        return i;
    }
}
