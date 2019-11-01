package com.midnight.auth.repository;

import com.midnight.auth.model.PlatformUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformUserRepository extends CrudRepository<PlatformUser,String> {

    @Query(value = "SELECT * FROM platform_user where delete_flag=0 and enable_flag=1 and username=:username",nativeQuery=true)
    public PlatformUser loadUserByUsername(String username);
}
