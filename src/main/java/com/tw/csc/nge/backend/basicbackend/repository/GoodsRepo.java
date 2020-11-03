package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsRepo extends PagingAndSortingRepository<GoodsPo, Long>{
    
}
