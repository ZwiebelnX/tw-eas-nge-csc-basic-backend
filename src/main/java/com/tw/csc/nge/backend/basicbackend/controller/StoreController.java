package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.store.StoreInfoDto;
import com.tw.csc.nge.backend.basicbackend.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController{

    private final StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/{storeId}")
    @ResponseStatus(HttpStatus.OK)
    public StoreInfoDto getStoreInfo(@PathVariable long storeId){
        return storeService.getStoreInfo(storeId);
    }

    @GetMapping("/{storeId}/goods")
    @ResponseStatus(HttpStatus.OK)
    public PageableDto<GoodsDto> getGoodsListByStore(@PathVariable long storeId, @RequestParam int pageNum,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize){
        if(pageNum <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页码不能小于1");
        }
        if(pageSize <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页大小不能小于1");
        }

        return storeService.getStoreGoodsList(storeId, pageNum, pageSize);
    }
}
