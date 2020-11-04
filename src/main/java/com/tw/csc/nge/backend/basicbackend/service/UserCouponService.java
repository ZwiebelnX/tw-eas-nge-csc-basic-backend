package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.UserCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CouponInfoPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserCouponPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserCouponRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    public PageableDto<UserCouponDto> getUserCouponList(int pageNum, int pageSize, long userId){
        UserPo userPo = userService.getUserPo(userId);

        Page<UserCouponPo> userCouponPoPage = userCouponRepo.findByUserPo(userPo,
                                                                          PageRequest.of(pageNum - 1, pageSize));

        PageableDto<UserCouponDto> userCouponListDto =
                PageableDto.<UserCouponDto>builder().totalPages(userCouponPoPage.getTotalPages())
                                                    .data(new ArrayList<>())
                                                    .build();
        userCouponPoPage.forEach((value) -> userCouponListDto.getData()
                                                             .add(PoToDtoTransformer.userCouponPoToUserCouponDto(value)));

        return userCouponListDto;
    }
}
