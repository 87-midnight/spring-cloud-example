package com.lcg.example.data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReduceBalanceRequestVO {

    private Long userId;

    private Integer price;
}