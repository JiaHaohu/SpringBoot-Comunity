package com.hjh.community_test.service;

import com.hjh.community_test.mapper.UserMapper;
import com.hjh.community_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void create(User user) {
        User dbuser=userMapper.findByAccountId(user.getAccountId());
        if (dbuser ==null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModfied(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            dbuser.setGmtModfied(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
