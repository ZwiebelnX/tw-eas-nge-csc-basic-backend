package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionController{

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> argumentErrorHandler(MethodArgumentNotValidException e){
        StringBuilder errorMessageBuilder = new StringBuilder();
        for(ObjectError error: e.getBindingResult().getAllErrors()){
            errorMessageBuilder.append(error.getDefaultMessage()).append(" ");
        }
        ErrorDto errorDto = ErrorDto.builder()
                                    .errorCode(BusinessExceptionType.JSON_ARGUMENT_ILLEGAL.getErrorCode())
                                    .errorMessage(errorMessageBuilder.toString())
                                    .build();
        return new ResponseEntity<>(errorDto, BusinessExceptionType.JSON_ARGUMENT_ILLEGAL.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> errorHandler(Exception e){
        e.printStackTrace();
        ErrorDto errorDto;
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException)e;
            errorDto = ErrorDto.builder()
                               .errorCode(businessException.getErrorType().getErrorCode())
                               .errorMessage(e.getMessage())
                               .build();
            return ResponseEntity.status(businessException.getErrorType().getHttpStatus()).body(errorDto);
        } else{
            errorDto = ErrorDto.builder().errorCode("50000").errorMessage(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
        }

    }
}
