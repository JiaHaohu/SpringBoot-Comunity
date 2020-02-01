package com.hjh.community_test.mapper;

import com.hjh.community_test.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("insert into comments(partent_Id,type,commentor,gmt_Create,gmt_Modfied,like_count,content) values(#{partenId},#{type},#{commentor},#{gmtCreate},#{gmtModfied},#{likeCount},#{content})")
    void insert(Comment comment);

}
