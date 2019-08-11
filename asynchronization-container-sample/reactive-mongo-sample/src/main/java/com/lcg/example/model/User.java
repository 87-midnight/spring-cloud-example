package com.lcg.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private Long id;
    private String username;
    private String address;
}
