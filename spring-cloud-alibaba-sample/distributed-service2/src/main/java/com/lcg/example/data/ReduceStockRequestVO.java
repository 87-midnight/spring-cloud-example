package com.lcg.example.data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReduceStockRequestVO {

    private Long productId;

    private Integer amount;
}