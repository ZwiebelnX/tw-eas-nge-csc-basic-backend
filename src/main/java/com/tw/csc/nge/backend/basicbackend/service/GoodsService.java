package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.repository.GoodsRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoodsService{

    private final GoodsRepo goodsRepo;

    private final StoreService storeService;

    public GoodsService(GoodsRepo goodsRepo, StoreService storeService){
        this.goodsRepo = goodsRepo;
        this.storeService = storeService;
    }

    public PageableDto<GoodsDto> getGoodsList(int pageNum, int pageSize){
        if(pageNum <= 0 || pageSize <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页码不能小于1");
        }

        Page<GoodsPo> goodsPoPage = goodsRepo.findAll(PageRequest.of(pageNum - 1, pageSize));
        PageableDto<GoodsDto> goodsPageableDto = new PageableDto<>();
        goodsPageableDto.setTotalPages(goodsPoPage.getTotalPages());
        goodsPageableDto.setData(new ArrayList<>());

        goodsPoPage.forEach((goodsPo -> goodsPageableDto.getData().add(PoToDtoTransformer.goodsPoToGoodsDto(goodsPo))));
        return goodsPageableDto;
    }

    public GoodsPo getGoodsPo(long id){
        return goodsRepo.findById(id).orElseThrow(() -> new BusinessException(BusinessExceptionType.GOODS_NOT_FOUND));
    }
}

