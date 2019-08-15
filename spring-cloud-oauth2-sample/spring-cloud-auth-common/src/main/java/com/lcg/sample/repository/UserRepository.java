package com.lcg.sample.repository;

import com.lcg.sample.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SysUser,Long> {

    SysUser findByUsername(String username);
}
