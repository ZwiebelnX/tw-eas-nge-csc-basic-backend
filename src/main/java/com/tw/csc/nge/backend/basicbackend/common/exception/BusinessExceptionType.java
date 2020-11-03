package com.tw.csc.nge.backend.basicbackend.common.exception;

import org.springframework.http.HttpStatus;

public enum BusinessExceptionType{

    JSON_ARGUMENT_ILLEGAL("40001", "JSON请求参数非法", HttpStatus.UNPROCESSABLE_ENTITY),
    GET_ARGUMENT_ILLEGAL("42202", "GET请求参数非法", HttpStatus.UNPROCESSABLE_ENTITY),
    USER_EXIST("40002", "昵称或邮箱已存在", HttpStatus.CONFLICT),
    LOGIN_INFO_ILLEGAL("40101", "用户名或密码错误", HttpStatus.UNAUTHORIZED);

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
