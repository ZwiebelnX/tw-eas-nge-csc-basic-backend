package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.po.CouponInfoPo;
import com.tw.csc.nge.backend.basicbackend.repository.CouponInfoRepo;
import org.springframework.stereotype.Service;

@Service
public class CouponInfoService{

    private final CouponInfoRepo couponInfoRepo;

    public CouponInfoService(CouponInfoRepo couponInfoRepo){
        this.couponInfoRepo = couponInfoRepo;
    }

    public CouponInfoPo getCouponInfoPo(long id){
        return couponInfoRepo.findById(id)
                             .orElseThrow(() -> new BusinessException(BusinessExceptionType.COUPON_INFO_NOT_FOUND));
    }
}
