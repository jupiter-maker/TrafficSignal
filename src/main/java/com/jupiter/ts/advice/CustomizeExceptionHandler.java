package com.jupiter.ts.advice;

import com.alibaba.fastjson.JSON;
import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model,
                                           HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json; charset=UTF-8".equals(contentType)){
            TsResultDto tsResultDto = null;
            //返回json
            if(e instanceof CustomizeException){
                //自定义异常
                tsResultDto = TsResultDto.build((CustomizeException)e);
            }else{
                //系统异常
                tsResultDto = TsResultDto.build(CustomizeErrorCode.SYS_ERROR);
            }
            try{
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(tsResultDto));
                writer.close();
            }catch (IOException ioe){
            }
            return null;

        }else{
            //页面跳转
            if(e instanceof CustomizeException){
                model.addAttribute("message",((CustomizeException) e).getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMsg());
            }
            return new ModelAndView("error");
        }

    }



}
