package com.tw.csc.nge.backend.basicbackend.utils;

import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;

public class PoToDtoTransformer{

    public static GoodsDto goodsPoToGoodsDto(GoodsPo goodsPo){
        return GoodsDto.builder()
                       .id(String.valueOf(goodsPo.getId()))
                       .description(goodsPo.getDescription())
                       .imageUrl(goodsPo.getImageUrl())
                       .name(goodsPo.getName())
                       .price(goodsPo.getPrice())
                       .storeId(String.valueOf(goodsPo.getStorePO().getId()))
                       .storeName(goodsPo.getStorePO().getName())
                       .build();
    }
}
