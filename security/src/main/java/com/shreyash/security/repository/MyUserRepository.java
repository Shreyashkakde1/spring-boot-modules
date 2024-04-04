package com.shreyash.security.repository;

import com.shreyash.security.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByUsername(String username);
}
