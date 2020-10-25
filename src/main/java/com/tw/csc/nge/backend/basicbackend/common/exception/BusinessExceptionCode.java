package com.tw.csc.nge.backend.basicbackend.common.exception;

public enum BusinessExceptionCode{

    JSON_ARGUMENT_ILLEGAL("40001");

    private final String value;

    BusinessExceptionCode(String errorCode){
        this.value = errorCode;
    }

    public String getValue(){
        return value;
    }
}
