package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.CartPo;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartRepo extends PagingAndSortingRepository<CartPo, Long>{

    Page<CartPo> findByUserPO(UserPo userPo, Pageable pageable);

    CartPo findByGoodsPO(GoodsPo goodsPo);
}
