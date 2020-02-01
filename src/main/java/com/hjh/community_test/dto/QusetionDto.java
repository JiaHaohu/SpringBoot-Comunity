package com.hjh.community_test.dto;

import com.hjh.community_test.model.User;
import lombok.Data;

@Data
public class QusetionDto {

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
    private User user;
    private String avatarUrl;

}
