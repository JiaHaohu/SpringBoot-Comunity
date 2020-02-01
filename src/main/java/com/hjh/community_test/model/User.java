package com.hjh.community_test.model;


import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModfied;
    private String avatarUrl;

}
