package com.hjh.community_test.dto;

import lombok.Data;

@Data
public class CommentDto
{
    private Long parentId;
    private String content;
    private int type;
}
