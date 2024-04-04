package com.shreyash.OTPExample.Service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendOTPEmail(String email);
    public String generateOtp();
}
