package com.lcg.sample.repository;

import com.lcg.sample.model.SysUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SysUser,Long> {

    SysUser getByAccountAndPassword(String account,String passwordEncode);
}
