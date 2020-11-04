package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController{

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService){
        this.goodsService = goodsService;
    }


    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public PageableDto<GoodsDto> getGoodsList(@RequestParam int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize){
        if(pageNum <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页码不能小月1");
        }
        if(pageSize <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页大小不能小于1");
        }
        
        return goodsService.getGoodsList(pageNum, pageSize);
    }


}
