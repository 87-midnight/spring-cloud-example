package com.lcg.example.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPayload implements Serializable {

    private String id;
    private String payload;
    private Date time;
}
