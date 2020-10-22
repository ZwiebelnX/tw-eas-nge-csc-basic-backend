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
@Table(name = "store")
public class StorePO extends BasicPO {

    @Column(length = 16)
    private String name;

    @Column(length = 128)
    private String description;

}
