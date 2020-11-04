package com.tw.csc.nge.backend.basicbackend.model.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticInfoDto{

    private String name;

    private String value;

    private String unit;
}
