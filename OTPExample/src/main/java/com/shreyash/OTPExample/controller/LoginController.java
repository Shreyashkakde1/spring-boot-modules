package com.shreyash.OTPExample.controller;

import com.shreyash.OTPExample.Service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/send-otp-for-login")
    public ResponseEntity<Map<String,String>> sendOptForLogin(@RequestParam String email){
        Map<String, String> stringStringMap = emailVerificationService.sendOtpForLogin(email);
        return new ResponseEntity<>(stringStringMap, HttpStatus.OK);
    }

    @PostMapping("/verify-otp-for-login")
    public ResponseEntity<Map<String,String>> verifyOtpForLogin(@RequestParam String email,@RequestParam String otp){
        Map<String, String> stringStringMap = emailVerificationService.verifyOtpForLogin(email, otp);
        return new ResponseEntity<>(stringStringMap,HttpStatus.OK);
    }
}
