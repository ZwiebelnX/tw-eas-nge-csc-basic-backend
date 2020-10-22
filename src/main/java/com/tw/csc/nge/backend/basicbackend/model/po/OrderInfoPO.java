package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_info")
public class OrderInfoPO extends BasicPO {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserPO userPO;

    @Column
    private int totalPrice;

    @Column(length = 32)
    private String phone;

    @Column(length = 128)
    private String address;


}
