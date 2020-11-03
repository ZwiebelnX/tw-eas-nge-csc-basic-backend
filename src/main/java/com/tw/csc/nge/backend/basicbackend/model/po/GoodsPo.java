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

    @Column(length = 128)
    private String name;

    @Column
    private int price;

    @Column(length = 128)
    private String description;

    @Column(length = 512)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StorePo storePO;


}
