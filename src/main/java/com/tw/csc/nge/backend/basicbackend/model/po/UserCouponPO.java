package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_coupon")
public class UserCouponPO extends BasicPO{

    @ManyToOne
    private UserPO userPO;

    @ManyToOne
    private CouponInfoPO couponPO;
}
