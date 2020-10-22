package com.tw.csc.nge.backend.basicbackend.model.po;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private StorePO storePO;


}
