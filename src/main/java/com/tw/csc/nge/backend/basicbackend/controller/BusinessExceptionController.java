package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionController{


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> argumentErrorHandler(MethodArgumentNotValidException e){
        ErrorDto errorDto = ErrorDto.builder()
                                    .errorCode(BusinessExceptionType.JSON_ARGUMENT_ILLEGAL.getErrorCode())
                                    .message(e.getMessage())
                                    .build();
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> errorHandler(Exception e){
        ErrorDto errorDto;
        if (e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            errorDto = ErrorDto.builder()
                               .errorCode(businessException.getErrorType().getErrorCode())
                               .message(e.getMessage())
                               .build();
            return ResponseEntity.status(businessException.getErrorType().getHttpStatus()).body(errorDto);
        } else{
            errorDto = ErrorDto.builder().errorCode("50000").message(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
        }

    }
}
