package com.tw.csc.nge.backend.basicbackend.model.po;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart")
public class CartPo extends BasicPo{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPo userPO;

    @ManyToOne
    @JoinColumn(name = "goods_id", nullable = false)
    private GoodsPo goodsPO;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private boolean isValid;

}
