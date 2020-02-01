package com.hjh.community_test.service;

import com.hjh.community_test.dto.PaginationDto;
import com.hjh.community_test.dto.QusetionDto;
import com.hjh.community_test.exception.CostmizrErrorCode;
import com.hjh.community_test.exception.CustomizeException;
import com.hjh.community_test.mapper.QusetionMapper;
import com.hjh.community_test.mapper.UserMapper;
import com.hjh.community_test.model.Question;
import com.hjh.community_test.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QusetionMapper qusetionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Integer page, Integer size) {

        PaginationDto paginationDto= new PaginationDto();
        Integer totalCount=qusetionMapper.count();
        paginationDto.setPagination(totalCount,page,size);

        System.out.println(size);
        Integer offset = size*(page-1);
        List<Question>  questions = qusetionMapper.list(offset,size);
        List<QusetionDto> qusetionDtos =new ArrayList<>();
        for (Question question:questions) {
            User user = userMapper.findById(question.getCreator());
            QusetionDto qusetionDto = new QusetionDto();
            BeanUtils.copyProperties(question,qusetionDto);
            System.out.println(user);
            qusetionDto.setAvatarUrl(user.getAvatarUrl());
            qusetionDto.setUser(user);
            qusetionDtos.add(qusetionDto);
        }
        paginationDto.setQusetions(qusetionDtos);

        return paginationDto;
    }



    public PaginationDto list(Integer id, Integer page, Integer size) {

        PaginationDto paginationDto= new PaginationDto();
        Integer totalCount=qusetionMapper.countById(id);
        System.out.println(id+"---"+totalCount);
        paginationDto.setPagination(totalCount,page,size);

        System.out.println(size);
        Integer offset = size*(page-1);
        List<Question>  questions = qusetionMapper.listById(id,offset,size);
        List<QusetionDto> qusetionDtos =new ArrayList<>();
        for (Question question:questions) {
            User user = userMapper.findById(question.getCreator());
            QusetionDto qusetionDto = new QusetionDto();
            BeanUtils.copyProperties(question,qusetionDto);
            qusetionDto.setAvatarUrl(user.getAvatarUrl());
            qusetionDto.setUser(user);
            qusetionDtos.add(qusetionDto);
        }
        paginationDto.setQusetions(qusetionDtos);

        return paginationDto;

    }

    public QusetionDto getById(Integer id) {
        Question question=qusetionMapper.getById(id);
        if (question ==null){
            throw new CustomizeException(CostmizrErrorCode.QUESTION_NOT_Find);
        }
        QusetionDto qusetionDto = new QusetionDto();
        BeanUtils.copyProperties(question,qusetionDto);
        User user=userMapper.findById(question.getCreator());
        qusetionDto.setUser(user);
        return qusetionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            qusetionMapper.create(question);
        }else {
            int status=qusetionMapper.update(question);
            if (status!=1){
                throw new CustomizeException(CostmizrErrorCode.QUESTION_NOT_Find);
            }
        }
    }
}
