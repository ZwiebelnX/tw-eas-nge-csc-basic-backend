package com.tw.csc.nge.backend.basicbackend.common.exception;

import org.springframework.http.HttpStatus;

public class CustomBasicException extends RuntimeException{

    private final HttpStatus httpStatus;

    private final BusinessExceptionCode errorCode;

    public CustomBasicException(HttpStatus httpStatus, String message,
                                BusinessExceptionCode errorCode){
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public BusinessExceptionCode getErrorCode(){
        return errorCode;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}

