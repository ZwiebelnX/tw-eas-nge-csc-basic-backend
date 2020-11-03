package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.model.dto.cart.AddToCartDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.CartItemDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CartPo;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.CartRepo;
import com.tw.csc.nge.backend.basicbackend.utils.PoToDtoTransformer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService{

    private final CartRepo cartRepo;

    private final UserService userService;

    private final GoodsService goodsService;

    public CartService(CartRepo cartRepo, UserService userService, GoodsService goodsService){
        this.cartRepo = cartRepo;
        this.userService = userService;
        this.goodsService = goodsService;
    }


    @Transactional
    public CartItemDto addGoodsToCart(AddToCartDto addToCartDto, long userId){
        UserPo userPo = userService.getUserPo(userId);
        GoodsPo goodsPo = goodsService.getGoodsPo(Long.parseLong(addToCartDto.getGoodsId()));

        CartPo cartPo = cartRepo.findByGoodsPO(goodsPo);
        if(cartPo == null){
            cartPo = CartPo.builder()
                           .goodsPO(goodsPo)
                           .userPO(userPo)
                           .amount(addToCartDto.getAmount())
                           .isValid(true)
                           .build();
        } else{
            cartPo.setAmount(cartPo.getAmount() + addToCartDto.getAmount());
        }
        cartRepo.save(cartPo);

        return CartItemDto.builder()
                          .goodsInfo(PoToDtoTransformer.goodsPoToGoodsDto(cartPo.getGoodsPO()))
                          .amount(cartPo.getAmount())
                          .build();

    }
}
