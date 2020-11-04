package com.tw.csc.nge.backend.basicbackend.model.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCouponDto{
    @NotEmpty(message = "请输入优惠券ID")
    @Pattern(regexp = "\\d+", message = "优惠券ID格式错误")
    private String id;

    private Timestamp expireTime;
}
