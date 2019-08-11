package com.lcg.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Document(indexName = "user")
@Builder
public class User {

    @Id
    private Long id;

    private String username;
}
