package com.tw.csc.nge.backend.basicbackend.model.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartDto{
    @NotEmpty(message = "商品ID不能为空")
    @Pattern(regexp = "\\d+", message = "商品ID错误")
    private String goodsId;

    @Range(min = 1, max = 99, message = "添加数量在1到99之间")
    private int amount;
}
