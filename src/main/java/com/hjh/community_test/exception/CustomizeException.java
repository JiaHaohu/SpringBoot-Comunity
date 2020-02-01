package com.hjh.community_test.exception;

public class CustomizeException extends RuntimeException {
    private String  message;

    public CustomizeException(ICosutmizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }


    public String getMessage(){
        return message;
    }

}
