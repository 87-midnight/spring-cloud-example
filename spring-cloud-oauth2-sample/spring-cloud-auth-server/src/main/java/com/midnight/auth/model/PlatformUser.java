package com.midnight.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "platform_user")
@Getter
@Setter
public class PlatformUser {

    @Id
    @Column(name = "platform_user_id", nullable = false)
    private String platformUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
