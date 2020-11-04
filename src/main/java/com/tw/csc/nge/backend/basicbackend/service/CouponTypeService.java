package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.po.CouponTypePo;
import com.tw.csc.nge.backend.basicbackend.repository.CouponTypeRepo;
import org.springframework.stereotype.Service;

@Service
public class CouponTypeService{

    private final CouponTypeRepo couponTypeRepo;

    public CouponTypeService(CouponTypeRepo couponTypeRepo){
        this.couponTypeRepo = couponTypeRepo;
    }

    public CouponTypePo getCouponTypePo(long id){
        return couponTypeRepo.findById(id)
                             .orElseThrow(() -> new BusinessException(BusinessExceptionType.COUPON_TYPE_NOT_FOUND));
    }
}
