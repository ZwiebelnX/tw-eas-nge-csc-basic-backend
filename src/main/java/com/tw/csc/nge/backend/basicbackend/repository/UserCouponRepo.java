package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.UserCouponPo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserCouponRepo extends PagingAndSortingRepository<UserCouponPo, Long>{
}
