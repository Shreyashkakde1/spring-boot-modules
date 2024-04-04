package com.shreyash.OTPExample.Service.impl;

import com.shreyash.OTPExample.Service.EmailService;
import com.shreyash.OTPExample.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
import static com.shreyash.OTPExample.Service.impl.EmailVerificationServiceImpl.emailOTPMapping;


@Service
public class EmailServiceImpl implements EmailService {


    private JavaMailSender javaMailSender;
    private UserService userService;

    public EmailServiceImpl(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public String generateOtp(){
        Random ran = new Random();
        int randomNumber = 100000 + ran.nextInt(900000); // Generates a random number between 100000 and 999999
        String formattedNumber = String.format("%06d", randomNumber); // Formats the number to ensure it's 6 digits with leading zeros if necessary
        return formattedNumber;
    }

    @Override
    public void sendOTPEmail(String email) {
        String otp = generateOtp();
        emailOTPMapping.put(email,otp);
        sendEmail(email,"OTP for Email Verification","Your OTP is: "+otp);
    }

    private void sendEmail(String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shreyashkakde20@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
