package com.tw.csc.nge.backend.basicbackend.common.exception;

import org.springframework.http.HttpStatus;

public class UserExistException extends CustomBasicException{

    public UserExistException(){
        super(HttpStatus.CONFLICT, "用户名/邮箱已存在", BusinessExceptionCode.USER_EXIST);
    }
}
