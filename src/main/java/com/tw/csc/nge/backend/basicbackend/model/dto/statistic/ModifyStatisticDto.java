package com.tw.csc.nge.backend.basicbackend.model.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyStatisticDto{
    @NotEmpty
    private String statisticName;

    @NotNull
    private int modifyValue;
}
