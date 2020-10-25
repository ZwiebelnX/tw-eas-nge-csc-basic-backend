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

    @Column(length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StorePo storePO;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id")
    private CouponTypePo couponTypePO;


}
