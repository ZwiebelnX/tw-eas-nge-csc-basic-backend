package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.model.dto.cart.CartItemDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.ModifyCartDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.po.CartPo;
import com.tw.csc.nge.backend.basicbackend.model.po.GoodsPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.CartRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

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
    public CartItemDto addGoodsToCart(ModifyCartDto modifyCartDto, long userId){
        UserPo userPo = userService.getUserPo(userId);
        GoodsPo goodsPo = goodsService.getGoodsPo(Long.parseLong(modifyCartDto.getGoodsId()));

        CartPo cartPo = cartRepo.findByGoodsPOAndUserPO(goodsPo, userPo);
        if(cartPo == null){
            cartPo = CartPo.builder()
                           .goodsPO(goodsPo)
                           .userPO(userPo)
                           .amount(modifyCartDto.getAmount())
                           .isValid(true)
                           .build();
        } else{
            cartPo.setAmount(cartPo.getAmount() + modifyCartDto.getAmount());
        }
        cartRepo.save(cartPo);

        return PoToDtoTransformer.cartPoToCartItemDto(cartPo);

    }

    public PageableDto<CartItemDto> getCartList(long userId, int pageNum, int pageSize){
        UserPo userPo = userService.getUserPo(userId);
        Page<CartPo> cartPoPage = cartRepo.findByUserPO(userPo, PageRequest.of(pageNum - 1, pageSize));
        PageableDto<CartItemDto> cartItemListDto =
                PageableDto.<CartItemDto>builder().data(new ArrayList<>())
                                                  .totalPages(cartPoPage.getTotalPages())
                                                  .build();

        cartPoPage.forEach(cartPo -> cartItemListDto.getData().add(PoToDtoTransformer.cartPoToCartItemDto(cartPo)));
        return cartItemListDto;
    }

    @Transactional
    public CartItemDto reduceGoodsInCart(ModifyCartDto modifyCartDto, long userId){
        UserPo userPo = userService.getUserPo(userId);
        GoodsPo goodsPo = goodsService.getGoodsPo(Long.parseLong(modifyCartDto.getGoodsId()));
        CartPo cartPo = cartRepo.findByGoodsPOAndUserPO(goodsPo, userPo);
        int newAmount = cartPo.getAmount() - modifyCartDto.getAmount();
        if(newAmount <= 0){
            cartRepo.delete(cartPo);
            return null;
        } else{
            cartPo.setAmount(newAmount);
            cartRepo.save(cartPo);
            return PoToDtoTransformer.cartPoToCartItemDto(cartPo);
        }
    }
}
