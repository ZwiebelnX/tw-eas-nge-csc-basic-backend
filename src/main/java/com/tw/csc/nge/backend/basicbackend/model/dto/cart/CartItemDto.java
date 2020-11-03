package com.tw.csc.nge.backend.basicbackend.model.dto.cart;

import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto{

    private GoodsDto goodsInfo;

    private int amount;
}
