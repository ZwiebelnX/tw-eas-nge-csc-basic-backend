package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_coupon")
public class UserCouponPo extends BasicPo{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPo userPO;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private CouponInfoPo couponPO;

    private Timestamp expireTime;
}
