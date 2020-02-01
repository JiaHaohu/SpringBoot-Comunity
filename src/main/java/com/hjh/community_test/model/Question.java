package com.hjh.community_test.model;


import lombok.Data;

@Data
public class Question
{
    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Integer creator;
    private Long gmtCreate;
    private Long gmtModfied;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


}
