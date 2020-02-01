package com.hjh.community_test.Controller;

import com.hjh.community_test.dto.PaginationDto;
import com.hjh.community_test.mapper.UserMapper;
import com.hjh.community_test.model.User;
import com.hjh.community_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "1") Integer size){


        User user =(User) request.getSession().getAttribute("user");

        if (user==null){
            return "redirect:/";
        }

        if (action.equals("question")){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }else if (action.equals("reply")){
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","我的回复");
        }

        PaginationDto paginationDto=questionService.list(user.getId(),page,size);
        model.addAttribute("paginationDto",paginationDto);
        return "profile";
    }

}
