package com.hjh.community_test.Controller;

import com.hjh.community_test.dto.QusetionDto;
import com.hjh.community_test.mapper.QusetionMapper;
import com.hjh.community_test.model.Question;
import com.hjh.community_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QusetionMapper qusetionMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        QusetionDto qusetionDto = questionService.getById(id);
        model.addAttribute("questionDto",qusetionDto);
        qusetionMapper.inVeiwCount(id);
        return  "question";
    }

}
