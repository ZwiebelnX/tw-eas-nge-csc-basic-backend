package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "goods")
public class GoodsPO extends BasicPO{

    @Column(length = 128)
    private String name;

    @Column
    private int price;

    @Column(length = 128)
    private String description;

    @Column(length = 256)
    private String imageUrl;




}
