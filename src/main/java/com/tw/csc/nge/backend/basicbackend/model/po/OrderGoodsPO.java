package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_goods")
public class OrderGoodsPO extends BasicPO {

    @ManyToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfoPO orderInfoPO;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private GoodsPO goodsPO;

    @Column
    private int amount;
}
