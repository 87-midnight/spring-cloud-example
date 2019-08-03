package com.lcg.example.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;

    private Long userId;

    private Long productId;

    private OrderStatus status;

    private Integer payAmount;
}
