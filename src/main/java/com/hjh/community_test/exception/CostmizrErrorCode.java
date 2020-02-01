package com.hjh.community_test.exception;

public enum CostmizrErrorCode implements ICosutmizeErrorCode {

    QUESTION_NOT_Find("问题找不到，要不换一个试试");

    private String message;

    CostmizrErrorCode(String message){
        this.message =message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
