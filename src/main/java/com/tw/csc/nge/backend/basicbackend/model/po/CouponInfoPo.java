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
public class CouponInfoPo extends BasicPo{

    @Column(length = 64, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StorePo storePO;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id", nullable = false)
    private CouponTypePo couponTypePO;


}
