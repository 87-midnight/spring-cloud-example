package com.lcg.example.data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestVO {
    private Long userId;

    private Long productId;

    private Integer price;
}