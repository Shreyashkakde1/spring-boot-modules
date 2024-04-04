package com.shreyash.OTPExample.Service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmailVerificationService {
    Map<String, String> verifyOtp(String email, String otp);
    public Map<String,String> sendOtpForLogin(String email);

    Map<String, String> verifyOtpForLogin(String email, String otp);
}
