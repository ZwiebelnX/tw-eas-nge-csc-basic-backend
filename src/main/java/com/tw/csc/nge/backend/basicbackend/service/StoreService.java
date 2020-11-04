package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.store.StoreInfoDto;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.StorePo;
import com.tw.csc.nge.backend.basicbackend.repository.GoodsRepo;
import com.tw.csc.nge.backend.basicbackend.repository.StoreRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StoreService{

    private final StoreRepo storeRepo;

    private final GoodsRepo goodsRepo;

    public StoreService(StoreRepo storeRepo, GoodsRepo goodsRepo){
        this.storeRepo = storeRepo;
        this.goodsRepo = goodsRepo;
    }

    public StorePo getStorePo(long id){
        return storeRepo.findById(id).orElseThrow(() -> new BusinessException(BusinessExceptionType.STORE_NOT_FOUND));
    }

    public StoreInfoDto getStoreInfo(long storeId){
        return PoToDtoTransformer.storePoToStoreInfoDto(getStorePo(storeId));
    }

    public PageableDto<GoodsDto> getStoreGoodsList(long storeId, int pageNum, int pageSize){
        StorePo storePo = getStorePo(storeId);
        Page<GoodsPo> goodsPoPage = goodsRepo.findByStorePO(storePo, PageRequest.of(pageNum - 1, pageSize));

        PageableDto<GoodsDto> goodsListDto = PageableDto.<GoodsDto>builder()
                .totalPages(goodsPoPage.getTotalPages())
                .data(new ArrayList<>()).build();

        goodsPoPage.forEach((value) -> goodsListDto.getData().add(PoToDtoTransformer.goodsPoToGoodsDto(value)));

        return goodsListDto;
    }
}
