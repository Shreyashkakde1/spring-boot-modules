package com.shreyash.OTPExample.Service.impl;

import com.shreyash.OTPExample.Service.UserService;
import com.shreyash.OTPExample.entity.User;
import com.shreyash.OTPExample.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User getUserByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail;
    }

    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.isEmailVerified();
    }
}
