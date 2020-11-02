package com.tw.csc.nge.backend.basicbackend.common.exception;

import org.springframework.http.HttpStatus;

public enum BusinessExceptionType{

    JSON_ARGUMENT_ILLEGAL("40001", "", HttpStatus.UNPROCESSABLE_ENTITY),
    USER_EXIST("40002", "昵称或邮箱已存在", HttpStatus.CONFLICT);

    private final String errorCode;

    private final String errorMessage;

    private final HttpStatus httpStatus;

    BusinessExceptionType(String errorCode, String errorMessage, HttpStatus httpStatus){

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
