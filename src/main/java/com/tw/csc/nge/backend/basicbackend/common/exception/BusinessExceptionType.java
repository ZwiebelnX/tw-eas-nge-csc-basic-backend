package com.tw.csc.nge.backend.basicbackend.common.exception;

import org.springframework.http.HttpStatus;

public enum BusinessExceptionType{

    JSON_ARGUMENT_ILLEGAL("42201", "JSON请求参数非法", HttpStatus.UNPROCESSABLE_ENTITY),
    GET_ARGUMENT_ILLEGAL("42202", "GET请求参数非法", HttpStatus.UNPROCESSABLE_ENTITY),
    GOODS_NOT_FOUND("40401", "请求的商品未找到", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("40402", "请求的用户未找到", HttpStatus.NOT_FOUND),
    COUPON_TYPE_NOT_FOUND("40403", "请求的优惠券类型未找到", HttpStatus.NOT_FOUND),
    COUPON_INFO_NOT_FOUND("40404", "请求的优惠券信息未找到", HttpStatus.NOT_FOUND),
    USER_COUPON_NOT_FOUND("40405", "请求的用户优惠券未找到", HttpStatus.NOT_FOUND),
    STATISTIC_NOT_FOUND("40406", "请求的用户优惠券未找到", HttpStatus.NOT_FOUND),
    STORE_NOT_FOUND("40406", "请求的商店未找到", HttpStatus.NOT_FOUND),
    USER_EXIST("40902", "昵称或邮箱已存在", HttpStatus.CONFLICT),
    USER_TOKEN_ILLEGAL("40101", "登录凭证无效", HttpStatus.UNAUTHORIZED),
    USER_NOT_ADMIN("40301", "用户不具有权限", HttpStatus.FORBIDDEN);

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
