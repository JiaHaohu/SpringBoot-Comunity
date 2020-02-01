package com.hjh.community_test.Controller;

import com.hjh.community_test.dto.CommentDto;
import com.hjh.community_test.dto.ResultDto;
import com.hjh.community_test.mapper.CommentMapper;
import com.hjh.community_test.model.Comment;
import com.hjh.community_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @ResponseBody
    @GetMapping("/comment")
    public Object post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDto.errorOf(2002,"未登录不能进行评论");
        }
        Comment comment = new Comment();
        comment.setPartentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModfied(comment.getGmtCreate());
        comment.setCommentor(user.getId());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }

}
