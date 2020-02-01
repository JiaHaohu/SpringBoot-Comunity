package com.hjh.community_test.Controller;


import com.hjh.community_test.mapper.QusetionMapper;
import com.hjh.community_test.model.Question;
import com.hjh.community_test.model.User;
import com.hjh.community_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishContorller {

    @Autowired
    private QusetionMapper qusetionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){

        Question question = qusetionMapper.getById(id);

        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTags());
        model.addAttribute("id",question.getId());

        return "publish";
    }


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title==null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null || description.equals("")){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (tag==null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Cookie[] cookies = request.getCookies();

        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setTags(tag);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setGmtCreate((System.currentTimeMillis()));
        question.setGmtModfied(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }


}
