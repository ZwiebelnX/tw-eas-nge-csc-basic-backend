package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.AddToCartDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.CartItemDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.pageable.PageableDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController{

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDto addGoodsToCart(@Valid @RequestBody AddToCartDto addToCartDto,
                                      HttpSession httpSession){
        long userId = Long.parseLong(((UserDto)httpSession.getAttribute("userInfo")).getId());
        return cartService.addGoodsToCart(addToCartDto, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageableDto<CartItemDto> getGoodsList(@RequestParam int pageNum,
                                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                 HttpSession httpSession){
        if(pageNum <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页码不能小月1");
        }
        if(pageSize <= 0){
            throw new BusinessException(BusinessExceptionType.GET_ARGUMENT_ILLEGAL, "页大小不能小于1");
        }

        long userId = Long.parseLong(((UserDto)httpSession.getAttribute("userInfo")).getId());

        return cartService.getGoodsList(userId, pageNum, pageSize);

    }
}
