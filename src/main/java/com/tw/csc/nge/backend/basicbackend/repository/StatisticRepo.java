package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.StatisticPo;
import org.springframework.data.repository.CrudRepository;

public interface StatisticRepo extends CrudRepository<StatisticPo, Long>{
    
}
