package com.hjh.community_test.mapper;

import com.hjh.community_test.dto.QusetionDto;
import com.hjh.community_test.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QusetionMapper {

    @Select("select * from qusetion where creator=#{id} limit #{offset} , #{size}")
    List<Question> listById(@Param(value = "id") Integer id, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select * from qusetion  limit #{offset} , #{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Insert("insert into qusetion(title,description,gmt_Create,gmt_Modfied,creator,tags) values(#{title},#{description},#{gmtCreate},#{gmtModfied},#{creator},#{tags})")
    public void create(Question qusetion);

    @Select("select count(1) from qusetion")
    Integer count();

    @Select("select count(1) from qusetion where creator=#{id}")
    Integer countById(Integer id);

    @Select("select * from qusetion where id=#{id}")
    Question getById(Integer id);

    @Update("update qusetion set title=#{title},description=#{description},tags=#{tags},gmt_modfied=#{gmtModfied}  where id=#{id}")
    Integer update(Question question);

    @Update("update qusetion set view_count = view_count+1 where id = #{id}")
    void inVeiwCount(Integer id);
}
