package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.po.StorePo;
import com.tw.csc.nge.backend.basicbackend.repository.StoreRepo;
import org.springframework.stereotype.Service;

@Service
public class StoreService{

    private final StoreRepo storeRepo;

    public StoreService(StoreRepo storeRepo){
        this.storeRepo = storeRepo;
    }

    public StorePo getStorePo(long id){
        return storeRepo.findById(id).orElseThrow(() -> new BusinessException(BusinessExceptionType.STORE_NOT_FOUND));
    }
}
