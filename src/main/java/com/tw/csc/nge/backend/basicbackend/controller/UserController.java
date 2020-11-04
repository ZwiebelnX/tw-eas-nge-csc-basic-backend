package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.AddCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.UserCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody UserDto userDTO){
        return userService.registerUser(userDTO);
    }

    @PostMapping("/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCouponDto addUserCoupon(@Valid @RequestBody AddCouponDto addCouponDto, HttpSession httpSession){
        long userId = Long.parseLong(((UserDto)httpSession.getAttribute("userInfo")).getId());
        long couponId = Long.parseLong(addCouponDto.getId());
        return userService.addUserCoupon(userId, couponId, addCouponDto.getExpireTime());
    }
}
