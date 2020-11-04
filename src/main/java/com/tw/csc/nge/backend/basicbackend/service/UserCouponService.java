package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.UserCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CouponInfoPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserCouponPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserCouponRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class UserCouponService{
    private final UserCouponRepo userCouponRepo;

    private UserService userService;

    public UserCouponService(UserCouponRepo userCouponRepo){
        this.userCouponRepo = userCouponRepo;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Transactional
    public UserCouponDto addUserCoupon(long userId, CouponInfoPo couponInfoPo, Timestamp expireTime){
        UserPo userPo = userService.getUserPo(userId);
        UserCouponPo userCouponPo =
                UserCouponPo.builder().userPo(userPo).couponInfoPo(couponInfoPo).expireTime(expireTime).build();
        userCouponRepo.save(userCouponPo);
        return PoToDtoTransformer.userCouponPoToUserCouponDto(userCouponPo);

    }

}
