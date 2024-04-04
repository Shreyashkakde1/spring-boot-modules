package com.shreyash.OTPExample.Service;

import com.shreyash.OTPExample.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(User user);

    User getUserByEmail(String email);

    void verifyEmail(User user);

    boolean isEmailVerified(String email);
}
