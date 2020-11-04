package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.UserCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.service.UserCouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/coupons")
public class UserCouponController{

    private final UserCouponService userCouponService;

    public UserCouponController(UserCouponService userCouponService){
        this.userCouponService = userCouponService;
    }

    @GetMapping
    public PageableDto<UserCouponDto> getUserCouponList(@RequestParam int pageNum
            , @RequestParam(required = false, defaultValue = "10") int pageSize, HttpSession httpSession){
        if(pageNum <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页码不能小月1");
        }
        if(pageSize <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页大小不能小于1");
        }

        long userId = Long.parseLong(((UserDto)httpSession.getAttribute("userInfo")).getId());

        return userCouponService.getUserCouponList(pageNum, pageSize, userId);
    }
}
