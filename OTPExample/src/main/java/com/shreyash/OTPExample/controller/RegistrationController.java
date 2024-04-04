package com.shreyash.OTPExample.controller;

import com.shreyash.OTPExample.Service.EmailService;
import com.shreyash.OTPExample.Service.EmailVerificationService;
import com.shreyash.OTPExample.Service.UserService;
import com.shreyash.OTPExample.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    private UserService userService;

    private EmailService emailService;

    private EmailVerificationService emailVerificationService;

    public RegistrationController(UserService userService, EmailService emailService, EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody User user){
        // Register the user without email verification
        User savedUser = userService.registerUser(user);

        // Send OPT email for email verification
        emailService.sendOTPEmail(user.getEmail());

        Map<String,String> response = new HashMap<>();
        response.put("status","success");
        response.put("message","User Registered successfully. Check your email for verification");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOpt(@RequestParam String email,@RequestParam String otp){
        Map<String, String> mapResponseEntity = emailVerificationService.verifyOtp(email, otp);
        return mapResponseEntity;
    }
}
