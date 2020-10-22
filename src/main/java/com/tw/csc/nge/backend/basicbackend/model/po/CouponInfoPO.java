package com.tw.csc.nge.backend.basicbackend.model.po;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "coupon_info")
public class CouponInfoPO extends BasicPO {

    @Column(length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StorePO storePO;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id")
    private CouponTypePO couponTypePO;


}
