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
    @NotEmpty(message = "统计名称不能为空")
    private String statisticName;

    @NotNull(message = "修改值不能为空")
    private int modifyValue;
}
