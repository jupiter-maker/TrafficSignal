package com.jupiter.ts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //返回首页
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sectionId","brigades");
        model.addAttribute("sectionName","大队列表");
        return "index";
    }

    @GetMapping("/intersections")
    public String intersections(Model model){
        model.addAttribute("sectionId","intersections");
        model.addAttribute("sectionName","路口信息");
        return "/intersections";
    }
}
