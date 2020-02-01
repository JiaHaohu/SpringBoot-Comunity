package com.hjh.community_test.mapper;


import com.hjh.community_test.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Insert("insert into user(name,accountid,token,gmtcreate,gmtmodfied,avatar_Url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModfied},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where accountid=#{accountid}")
    User findByAccountId(String accountId);

    @Update("update user set name=#{name},token=#{token},gmtmodfied=#{gmtModfied}  where id=#{id}")
    void update(User dbuser);
}
