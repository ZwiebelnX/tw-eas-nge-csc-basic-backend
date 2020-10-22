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
public class CartPO extends BasicPO {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPO userPO;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private GoodsPO goodsPO;

    @Column
    private int amount;

    @Column
    private boolean isValid;

}
