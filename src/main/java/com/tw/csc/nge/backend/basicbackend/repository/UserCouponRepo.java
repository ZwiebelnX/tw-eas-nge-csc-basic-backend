package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.UserCouponPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserCouponRepo extends PagingAndSortingRepository<UserCouponPo, Long>{

    Page<UserCouponPo> findByUserPo(UserPo userPo, Pageable pageable);
}
