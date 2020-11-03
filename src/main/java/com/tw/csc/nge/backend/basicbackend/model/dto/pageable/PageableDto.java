package com.tw.csc.nge.backend.basicbackend.model.dto.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableDto<T>{
    private int totalPages;

    private List<T> data;
}
