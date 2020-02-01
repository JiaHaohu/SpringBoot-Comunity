package com.hjh.community_test.Controller;

import com.hjh.community_test.dto.PaginationDto;
import com.hjh.community_test.dto.QusetionDto;
import com.hjh.community_test.mapper.UserMapper;
import com.hjh.community_test.model.Question;
import com.hjh.community_test.model.User;

import com.hjh.community_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDto paginationDto =questionService.list(page,size);

        model.addAttribute("paginationDto",paginationDto);


        return "index";
    }

}
