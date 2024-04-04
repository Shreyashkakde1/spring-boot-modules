package com.shreyash.OTPExample.repository;

import com.shreyash.OTPExample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
