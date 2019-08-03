package com.lcg.example.data;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {

    private boolean success;

    private String message;

    private Object data;
}