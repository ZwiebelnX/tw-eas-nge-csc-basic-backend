package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionCode;
import com.tw.csc.nge.backend.basicbackend.common.exception.CustomBasicException;
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
                                    .errorCode(
                                            BusinessExceptionCode.JSON_ARGUMENT_ILLEGAL.getValue())
                                    .message(e.getMessage())
                                    .build();
        return ResponseEntity.badRequest()
                             .body(errorDto);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> errorHandler(Exception e){
        ErrorDto errorDto;
        if (e instanceof CustomBasicException){
            CustomBasicException customBasicException = (CustomBasicException) e;
            errorDto = ErrorDto.builder()
                               .errorCode(customBasicException.getErrorCode()
                                                              .getValue())
                               .message(e.getMessage())
                               .build();
            return ResponseEntity.status(customBasicException.getHttpStatus())
                                 .body(errorDto);
        } else{
            errorDto = ErrorDto.builder()
                               .errorCode("50000")
                               .message(e.getMessage())
                               .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(errorDto);
        }

    }
}
