package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.StatisticPo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatisticRepo extends CrudRepository<StatisticPo, Long>{
    Optional<StatisticPo> findByName(String name);
}
