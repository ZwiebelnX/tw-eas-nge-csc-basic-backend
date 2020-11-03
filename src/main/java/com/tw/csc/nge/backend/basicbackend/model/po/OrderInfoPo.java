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
public class OrderInfoPo extends BasicPo{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPo userPO;

    @Column(nullable = false)
    private int totalPrice;

    @Column(length = 32, nullable = false)
    private String phone;

    @Column(length = 128, nullable = false)
    private String address;


}
