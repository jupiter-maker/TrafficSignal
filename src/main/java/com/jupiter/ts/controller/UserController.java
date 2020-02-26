package com.jupiter.ts.controller;

import com.alibaba.fastjson.JSON;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import com.jupiter.ts.model.User;
import com.jupiter.ts.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验用户注册信息
     *
     * @param param
     * @param type
     * @return
     */
    @GetMapping("/check/{param}/{type}")
    @ResponseBody
    public TsResultDto checkData(@PathVariable String param, @PathVariable Integer type) {
        TsResultDto result = null;
        //参数有效性校验
        if (StringUtils.isBlank(param) || type == null) {
            result = TsResultDto.build(400, "校验内容或类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3) {
            result = TsResultDto.build(400, "校验内容错误");
        }
        if (null != result) {

            return result;
        }
        //调用服务
        System.out.println(param+"***********"+type);
        result = userService.checkData(param, type);

        return result;
    }

    /**
     * desc:创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public TsResultDto createUser(User user) {
        try {
            TsResultDto result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.USER_REGISTER_FAILED);
        }
    }

    /**
     * desc:用户登录
     */
    @PostMapping("/login")
    @ResponseBody
    public TsResultDto userLogin(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            TsResultDto result = userService.userLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            throw new CustomizeException(CustomizeErrorCode.USER_LOGIN_FAILED);
        }
    }


}
