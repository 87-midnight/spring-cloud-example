package com.midnight.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

//@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "platform_user")
@Getter
@Setter
public class PlatformUser {

    @Id
    @Column(name = "platform_user_id", nullable = false)
    @GeneratedValue(generator = "java-uuid")
    @GenericGenerator(name = "java-uuid",strategy = "uuid2") //构造器定义在DefaultIdentifierGeneratorFactory
    private UUID platformUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "enable_flag")
    private Integer enableFlag;
}
