package com.tw.csc.nge.backend.basicbackend.model.po;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BasicPO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @CreatedDate
    private Timestamp gmt_create;

    @LastModifiedDate
    private Timestamp gmt_modified;

}
