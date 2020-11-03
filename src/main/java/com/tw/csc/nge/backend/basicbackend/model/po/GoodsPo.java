package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "goods")
public class GoodsPo extends BasicPo{

    @Column(length = 128, nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(length = 128, nullable = false)
    private String description;

    @Column(length = 512, nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StorePo storePO;


}
