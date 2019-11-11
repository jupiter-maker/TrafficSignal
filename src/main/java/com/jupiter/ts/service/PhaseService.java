package com.jupiter.ts.service;

import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.mapper.PhaseMapper;
import com.jupiter.ts.mapper.ProjectMapper;
import com.jupiter.ts.model.Phase;
import com.jupiter.ts.model.PhaseExample;
import com.jupiter.ts.model.Project;
import com.jupiter.ts.model.ProjectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhaseService {

    @Autowired
    private PhaseMapper phaseMapper;
    @Autowired
    private ProjectMapper projectMapper;


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
        int i;
        try {
            //删除相位的同时，要将主相位id为该相位的方案主相位重置
            ProjectExample projectExample = new ProjectExample();
            projectExample.createCriteria().andFaZxwEqualTo(id);
            List<Project> projects = projectMapper.selectByExample(projectExample);
            if (projects != null && projects.size() != 0) {
                for (Project p : projects) {
                    p.setFaModified(System.currentTimeMillis());
                    p.setFaZxwName(null);
                    p.setFaZxw(null);
                    projectMapper.updateByPrimaryKey(p);
                }
            }
            i = phaseMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.PHASE_DELETE_FAILED);
        }
        return i;
    }
}
