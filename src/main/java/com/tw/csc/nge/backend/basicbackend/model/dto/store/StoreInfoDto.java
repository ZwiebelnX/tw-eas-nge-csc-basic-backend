package com.tw.csc.nge.backend.basicbackend.model.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreInfoDto{
    private String id;

    private String name;

    private String description;
}
