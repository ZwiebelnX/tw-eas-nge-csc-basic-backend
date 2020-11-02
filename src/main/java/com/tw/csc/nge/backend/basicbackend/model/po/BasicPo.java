package com.tw.csc.nge.backend.basicbackend.model.po;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BasicPo{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @CreatedDate
    private Timestamp gmt_create;

    @LastModifiedDate
    private Timestamp gmt_modified;

}
