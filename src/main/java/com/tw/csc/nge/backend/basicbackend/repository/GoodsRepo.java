package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.StorePo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsRepo extends PagingAndSortingRepository<GoodsPo, Long>{
    Page<GoodsPo> findByStorePO(StorePo storePo, Pageable pageable);
}
