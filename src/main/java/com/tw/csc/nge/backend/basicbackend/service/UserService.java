package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CouponInfoPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.DtoToPoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserService{

    private final UserRepo userRepo;

    private final CouponInfoService couponInfoService;

    private UserCouponService userCouponService;

    public UserService(UserRepo userRepo, CouponInfoService couponInfoService){
        this.userRepo = userRepo;
        this.couponInfoService = couponInfoService;
    }

    @Autowired
    public void setUserCouponService(UserCouponService userCouponService){
        this.userCouponService = userCouponService;
    }

    public UserDto registerUser(UserDto userDto){
        if(userRepo.existsByNicknameOrEmail(userDto.getNickname(), userDto.getEmail())){
            throw new BusinessException(BusinessExceptionType.USER_EXIST);
        }

        UserPo userPO = DtoToPoTransformer.userDtoToUserPo(userDto);

        userRepo.save(userPO);

        userDto.setId(String.valueOf(userPO.getId()));

        // company A requirement
        CouponInfoPo couponInfoPo = couponInfoService.getCouponInfoPo(2);
        userCouponService.addUserCoupon(userPO.getId(), couponInfoPo,
                                        new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));

        return userDto;
    }

    public UserPo getUserPo(long id){
        return userRepo.findById(id).orElseThrow(() -> new BusinessException(BusinessExceptionType.USER_NOT_FOUND));
    }
}
