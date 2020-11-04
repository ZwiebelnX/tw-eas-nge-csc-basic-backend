package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "statistic")
public class StatisticPo extends BasicPo{

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private int value;


    @Column(nullable = false, length = 16)
    private String unit;
}
