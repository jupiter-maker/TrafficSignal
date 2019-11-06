package com.jupiter.ts.controller;


import com.jupiter.ts.mapper.IntersectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private IntersectionMapper intersectionMapper;
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
        model.addAttribute("sectionName","路口信息统计");
        return "/intersections";
    }

//    @RequestMapping("/test")
//    @ResponseBody
//    public TsResultDto testAddIntersection(){
//        Intersection intersection = new Intersection();
//        //生成随机数
//        Random random = new Random();
//        for(int i = 0;i<1000;i++){
//            intersection.setIsCreate(System.currentTimeMillis());
//            intersection.setIsName("测试路口"+ UUID.randomUUID().toString().substring(0,3)+random.nextInt(100)); //随机路口名
//            intersection.setIsXhId(random.nextInt(3)+1);  //随机信号机型号
//            intersection.setIsWhName("维护人员"+UUID.randomUUID().toString().substring(0,2)); //随机维护人员
//            intersection.setIsDl("测试道路"+UUID.randomUUID().toString().substring(0,5)); //随机所属道路
//            intersection.setIsDdId(random.nextInt(9)+1); //随机大队id
//            intersection.setIsModified(System.currentTimeMillis());
//            intersectionMapper.insertSelective(intersection);
//        }
//        return TsResultDto.ok();
//    }
}
