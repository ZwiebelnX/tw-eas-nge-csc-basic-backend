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
public class OrderGoodsPo extends BasicPo{

    @ManyToOne
    @JoinColumn(name = "order_info_id", nullable = false)
    private OrderInfoPo orderInfoPO;

    @ManyToOne
    @JoinColumn(name = "goods_id", nullable = false)
    private GoodsPo goodsPO;

    @Column
    private int amount;
}
