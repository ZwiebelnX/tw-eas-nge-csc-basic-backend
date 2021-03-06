package com.tw.csc.nge.backend.basicbackend.model.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDto{
    private String id;

    private String storeId;

    private String storeName;

    private String description;

    private String imageUrl;

    private String name;

    private int price;


}
