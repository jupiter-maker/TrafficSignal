package com.jupiter.ts.controller;

import com.jupiter.ts.dto.BrigadeDto;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.model.Brigade;
import com.jupiter.ts.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 大队信息管理Controller
 */
@Controller
@RequestMapping("/brigade")
public class BrigadeController {

    @Autowired
    private BrigadeService brigadeService;
    /**
     * 返回所有大队信息
     * @return
     */
    @GetMapping("/all")
    @ResponseBody
    public TsResultDto getAllBrigade(){
        List<Brigade> brigades = brigadeService.getAllBrigade();
        return TsResultDto.ok(brigades);
    }

    /**
     * 返回大队统计信息
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public TsResultDto getBrigadeList(){
        List<BrigadeDto> brigadeList = brigadeService.getBrigadeList();
        if(brigadeList == null || brigadeList.size() == 0 ){
            return TsResultDto.build(300,"查询大队列表信息失败");
        }
        return TsResultDto.ok(brigadeList);
    }
}
