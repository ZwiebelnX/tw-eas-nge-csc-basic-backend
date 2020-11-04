package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.*;
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
    @JoinColumn(name = "user_id", nullable = false)
    private UserPo userPo;

    @ManyToOne
    @JoinColumn(name = "coupon_info_id", nullable = false)
    private CouponInfoPo couponInfoPo;

    @Column(nullable = false)
    private Timestamp expireTime;
}
