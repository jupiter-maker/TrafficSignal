package com.jupiter.ts.controller;


import com.jupiter.ts.model.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于不同页面之间的跳转
 */
@Controller
public class PageController {


    //返回首页
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "brigadesSts");
        model.addAttribute("sectionName", "大队信息统计");
        return "brigadesSts";
    }

    //显示登陆页面
    @GetMapping("/page/showLogin")
    public String showLogin(Model model) {
        model.addAttribute("sectionId", "brigadesSts");
        model.addAttribute("sectionName", "大队信息统计");
        return "login";
    }

    //显示注册页面
    @GetMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    /**
     * 路口信息管理页面
     *
     * @param model
     * @return
     */
    //编辑路口页面
    @GetMapping("/intersections/edit")
    public String intersectionsEdit(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "intersectionsEdit");
        model.addAttribute("sectionName", "路口信息编辑");
        return "intersectionsEdit";
    }

    //查询路口页面
    @GetMapping("/intersections/query")
    public String intersectionsQuery(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "intersectionsQuery");
        model.addAttribute("sectionName", "路口信息查询");
        return "intersectionsQuery";
    }

    //添加路口页面
    @GetMapping("/intersections/add")
    public String addIntersection(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "addIntersection");
        model.addAttribute("sectionName", "路口信息录入");
        return "intersectionAdd";
    }

    /**
     * 大队信息管理页面
     *
     * @param model
     * @return
     */
    @GetMapping("/brigades/add")
    public String addBrigades(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
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
    public String addRoads(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
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
    public String queryRoads(Model model,HttpServletRequest request) {
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "queryRoads");
        model.addAttribute("sectionName", "道路信息查询");
        return "roadsQuery";
    }

    /**
     * 方案添加页面
     */
    @GetMapping("/projects/add")
    public String projectAdd(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "projectAdd");
        model.addAttribute("sectionName", "方案信息录入");
        return "projectAdd";
    }
    /**
     * 方案查询页面
     */
    @GetMapping("/projects/query")
    public String projectsQuery(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "queryProjects");
        model.addAttribute("sectionName", "方案信息查询");
        return "projectsQuery";
    }
    /**
     * 方案编辑页面
     */
    @GetMapping("/projects/edit")
    public String projectsEdit(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "projectsEdit");
        model.addAttribute("sectionName", "方案信息编辑");
        return "projectsEdit";
    }

    /**
     * 统计页面
     */
    @GetMapping("/brigades/sts")
    public String brigadesSts(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "brigadesSts");
        model.addAttribute("sectionName", "大队信息统计");
        return "brigadesSts";
    }
    @GetMapping("/roads/sts")
    public String roadsSts(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "roadsSts");
        model.addAttribute("sectionName", "道路-路口-信息统计");
        return "roadsSts";
    }
    @GetMapping("/intersections/sts")
    public String intersectionsSts(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "intersectionsSts");
        model.addAttribute("sectionName", "路口-信息统计");
        return "intersectionsSts";
    }
    @GetMapping("/projects/sts")
    public String projectsSts(Model model,HttpServletRequest request){
        //校验用户是否登录
        User user = (User)request.getSession().getAttribute("user");
        if(user == null || user.getUsername() == null){
            return "login";
        }
        model.addAttribute("sectionId", "projectsSts");
        model.addAttribute("sectionName", "方案-路口统计");
        return "projectsSts";
    }
}
