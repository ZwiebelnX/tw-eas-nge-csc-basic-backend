package com.tw.csc.nge.backend.basicbackend.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.ErrorDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if(request.getSession() != null && request.isRequestedSessionIdValid()
           && request.getSession().getAttribute("userInfo") != null){
            return true;
        } else{
            response.setStatus(401);
            ErrorDto errorDto = ErrorDto.builder()
                                        .errorCode(BusinessExceptionType.USER_TOKEN_ILLEGAL.getErrorCode())
                                        .errorMessage(BusinessExceptionType.USER_TOKEN_ILLEGAL.getErrorMessage())
                                        .build();
            response.setContentType("application/json;encode=utf-8");
            response.getWriter().println(objectMapper.writeValueAsString(errorDto));
            return false;
        }
    }
}
