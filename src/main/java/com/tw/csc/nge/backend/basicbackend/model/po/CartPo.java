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
    @JoinColumn(name = "user_id")
    private UserPo userPO;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private GoodsPo goodsPO;

    @Column
    private int amount;

    @Column
    private boolean isValid;

}
