package com.jupiter.ts.controller;


import com.jupiter.ts.mapper.BrigadeExtMapper;
import com.jupiter.ts.mapper.IntersectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用于不同页面之间的跳转
 */
@Controller
public class PageController {


    //返回首页
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sectionId", "brigades");
        model.addAttribute("sectionName", "大队列表");
        return "index";
    }

    //大队信息统计页
    @GetMapping("/statistic/brigades")
    public String stsBrigadesPage(Model model) {
        model.addAttribute("sectionId", "brigades");
        model.addAttribute("sectionName", "大队列表");
        return "index";
    }

    //大队信息统计页
    @GetMapping("/statistic/brigade/roads")
    public String stsBrigadeRoadsPage(Model model) {
        model.addAttribute("sectionId", "brigades");
        model.addAttribute("sectionName", "大队辖区道路列表");
        return "brigadeRoads";
    }
    /**
     * 路口信息管理页面
     *
     * @param model
     * @return
     */
    //编辑路口页面
    @GetMapping("/intersections/edit")
    public String intersectionsEdit(Model model) {
        model.addAttribute("sectionId", "intersectionsEdit");
        model.addAttribute("sectionName", "路口信息编辑");
        return "intersectionsEdit";
    }

    //查询路口页面
    @GetMapping("/intersections/query")
    public String intersectionsQuery(Model model) {
        model.addAttribute("sectionId", "intersectionsQuery");
        model.addAttribute("sectionName", "路口信息查询");
        return "intersectionsQuery";
    }

    //添加路口页面
    @GetMapping("/intersections/add")
    public String addIntersection(Model model) {
        model.addAttribute("sectionId", "addIntersection");
        model.addAttribute("sectionName", "路口信息录入");
        return "intersectionsAdd";
    }

    /**
     * 大队信息管理页面
     *
     * @param model
     * @return
     */
    @GetMapping("/brigades/add")
    public String addBrigades(Model model) {
        model.addAttribute("sectionId", "addBrigades");
        model.addAttribute("sectionName", "大队信息录入");
        return "brigadesEdit";
    }

    /**
     * 道路信息管理页面
     *
     * @param model
     * @return
     */
    @GetMapping("/roads/add")
    public String addRoads(Model model) {
        model.addAttribute("sectionId", "addRoads");
        model.addAttribute("sectionName", "道路信息录入");
        return "roadsEdit";
    }

    /**
     * 道路信息查询页面
     *
     * @param model
     * @return
     */
    @GetMapping("/roads/query")
    public String queryRoads(Model model) {
        model.addAttribute("sectionId", "queryRoads");
        model.addAttribute("sectionName", "道路信息查询");
        return "roadsQuery";
    }

    /**
     * 方案添加页面
     */
    @GetMapping("/projects/add")
    public String projectAdd(Model model){
        model.addAttribute("sectionId", "projectAdd");
        model.addAttribute("sectionName", "方案信息录入");
        return "projectAdd";
    }
    /**
     * 方案查询页面
     */
    @GetMapping("/projects/query")
    public String projectQuery(Model model){
        model.addAttribute("sectionId", "queryProjects");
        model.addAttribute("sectionName", "方案信息查询");
        return "projectsQuery";
    }
}
