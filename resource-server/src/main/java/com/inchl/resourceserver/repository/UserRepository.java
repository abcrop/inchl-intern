package com.inchl.resourceserver.repository;

import com.inchl.resourceserver.entity.UserModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModelEntity, Long> {
    UserModelEntity findByEmail(String email);
    Boolean existsByEmail(String email);
}
