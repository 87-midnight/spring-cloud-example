package com.lcg.sample.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")
@Getter
@Setter
@ToString
public class SysUser extends BaseModel{

    private String userName;

    private String account;

    private String password;

}
