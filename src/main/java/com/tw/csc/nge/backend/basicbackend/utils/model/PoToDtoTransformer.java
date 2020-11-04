package com.tw.csc.nge.backend.basicbackend.utils.model;

import com.tw.csc.nge.backend.basicbackend.model.dto.cart.CartItemDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.coupon.UserCouponDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.goods.GoodsDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CartPo;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserCouponPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;

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

    public static CartItemDto cartPoToCartItemDto(CartPo cartPo){
        return CartItemDto.builder()
                          .amount(cartPo.getAmount())
                          .goodsInfo(goodsPoToGoodsDto(cartPo.getGoodsPO()))
                          .build();
    }

    public static UserDto userPoToUserDto(UserPo userPo){
        return UserDto.builder()
                      .id(String.valueOf(userPo.getId()))
                      .email(userPo.getEmail())
                      .nickname(userPo.getNickname())
                      .phone(userPo.getPhone())
                      .realName(userPo.getRealName())
                      .build();
    }

    public static UserCouponDto userCouponPoToUserCouponDto(UserCouponPo userCouponPo){
        return UserCouponDto.builder().couponName(userCouponPo.getCouponInfoPo().getName())
                            .storeId(String.valueOf(userCouponPo.getCouponInfoPo().getStorePO().getId()))
                            .storeName(userCouponPo.getCouponInfoPo().getStorePO().getName())
                            .expireTime(userCouponPo.getExpireTime()).build();
    }
}
