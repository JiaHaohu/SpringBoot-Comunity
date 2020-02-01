package com.hjh.community_test.advice;

import com.hjh.community_test.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView hanndle(HttpServletRequest request, Throwable ex, Model model){
        HttpStatus status = getSatus(request);
        model.addAttribute("message","服务器冒烟了。。。");
        if (ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else {
            model.addAttribute("message","服务器冒烟了。。。");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getSatus(HttpServletRequest request){
        Integer statusCode =(Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(statusCode);
    }

}
