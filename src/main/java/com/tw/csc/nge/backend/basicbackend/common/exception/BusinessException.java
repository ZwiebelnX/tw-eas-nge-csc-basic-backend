package com.tw.csc.nge.backend.basicbackend.common.exception;

public class BusinessException extends RuntimeException{

    private final BusinessExceptionType errorType;

    public BusinessException(BusinessExceptionType errorType){
        super(errorType.getErrorMessage());
        this.errorType = errorType;
    }

    public BusinessException(BusinessExceptionType errorType, String extraMessage){
        super(errorType.getErrorMessage() + " && 额外信息：" + extraMessage);
        this.errorType = errorType;
    }

    public BusinessExceptionType getErrorType(){
        return errorType;
    }
}

