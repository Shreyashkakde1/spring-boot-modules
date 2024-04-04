package com.shreyash.security.controller;

import com.shreyash.security.entity.MyUser;
import com.shreyash.security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register/user")
    public ResponseEntity<MyUser> registerUser(@RequestBody MyUser myUser){
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        MyUser save = userRepository.save(myUser);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
