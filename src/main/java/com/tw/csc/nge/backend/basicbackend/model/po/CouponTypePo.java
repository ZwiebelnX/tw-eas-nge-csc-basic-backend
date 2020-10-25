package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "coupon_type")
public class CouponTypePo extends BasicPo{

    @Column(length = 64)
    private String name;

    @Column(length = 128)
    private String comment;
}
