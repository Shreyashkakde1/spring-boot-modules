package com.shreyash.OTPExample.Service.impl;

import com.shreyash.OTPExample.Service.EmailService;
import com.shreyash.OTPExample.Service.EmailVerificationService;
import com.shreyash.OTPExample.Service.UserService;
import com.shreyash.OTPExample.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private UserService userService;
    private EmailService emailService;
    public static final Map<String,String> emailOTPMapping = new HashMap<>();

    public EmailVerificationServiceImpl(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    public Map<String,String> verifyOtp(String email, String otp){
        String storedOtp = emailOTPMapping.get(email);
        Map<String,String> response = new HashMap<>();
        if(storedOtp != null && storedOtp.equals(otp)){

            User user = userService.getUserByEmail(email);
            if(user != null){
                emailOTPMapping.remove(email);
                userService.verifyEmail(user);
                response.put("status","success");
                response.put("messsage","Email Verified Successfully");
            }else {
                response.put("status","error");
                response.put("status","User not found");
            }
        }else {
            response.put("status","error");
            response.put("message","Invalid Otp");
        }

        return response;
    }

    public Map<String,String> sendOtpForLogin(String email){
        if(userService.isEmailVerified(email)){
            String otp = emailService.generateOtp();
            emailOTPMapping.put(email,otp);

            emailService.sendOTPEmail(email);
            Map<String,String> response = new HashMap<>();
            response.put("status","success");
            response.put("message","OTP send Successfully");
            return response;
        }else {
            Map<String,String> response = new HashMap<>();
            response.put("status","error");
            response.put("message","Email Not Verified");
            return response;
        }
    }

    @Override
    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOTPMapping.get(email);

        Map<String,String> response = new HashMap<>();
        if(storedOtp != null && storedOtp.equals(otp)){
            emailOTPMapping.remove(email);
            // OTP is valid
            response.put("status","success");
            response.put("message","OTP Verification Successfully");
        }else {
            // OTP is Invalid
            response.put("status","Error");
            response.put("message","Invalid Otp");
        }
        return response;
    }
}
