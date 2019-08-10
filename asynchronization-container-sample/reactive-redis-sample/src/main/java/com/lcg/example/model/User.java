package com.lcg.example.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String gender;
    private Integer age;

    @CreatedDate
    private LocalDateTime createdDate;

}
