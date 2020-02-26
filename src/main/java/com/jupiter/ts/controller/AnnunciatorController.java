package com.jupiter.ts.controller;

import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.model.Annunciator;
import com.jupiter.ts.service.AnnunciatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 型号机型号Controller
 */
@Controller
@RequestMapping("/annunciator")
public class AnnunciatorController {

    @Autowired
    private AnnunciatorService annunciatorService;

    @GetMapping("/all")
    @ResponseBody
    public TsResultDto getAllAnnunciator(){
        List<Annunciator> annunciators = annunciatorService.getAllAnnunciator();
        if(annunciators == null || annunciators.size() == 0){
            return TsResultDto.build(CustomizeErrorCode.ANNUNCIATOR_NOT_FOUND);
        }
        return TsResultDto.ok(annunciators);
    }
}
