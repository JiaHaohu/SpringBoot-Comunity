package com.hjh.community_test.model;

import lombok.Data;

@Data
public class Comment {

    private int id;
    private Long partentId;
    private int type;
    private int commentor;
    private long gmtCreate;
    private long gmtModfied;
    private long likeCount;
    private String content;


}
