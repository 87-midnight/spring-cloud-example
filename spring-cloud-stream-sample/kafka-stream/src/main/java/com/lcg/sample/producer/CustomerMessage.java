package com.lcg.sample.producer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMessage {
    private String id;
    private Long timestamp;
}
