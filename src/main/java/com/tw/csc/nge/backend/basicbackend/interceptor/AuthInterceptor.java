package com.tw.csc.nge.backend.basicbackend.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(request.getSession() != null && request.isRequestedSessionIdValid()
           && request.getSession().getAttribute("userInfo") != null){
            return true;
        } else{
            response.setStatus(401);
            return false;
        }
    }
}
