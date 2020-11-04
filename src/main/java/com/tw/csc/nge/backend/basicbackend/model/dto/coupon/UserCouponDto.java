package com.tw.csc.nge.backend.basicbackend.model.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCouponDto{

    private String id;

    private String couponName;

    private String storeId;

    private String storeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Timestamp expireTime;
}
