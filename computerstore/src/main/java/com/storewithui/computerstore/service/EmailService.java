package com.storewithui.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String to, String token) {
        // Create verification link
        String verificationLink = "http://yourdomain.com/verify?token=" + token;

        // Create email content
        String subject = "Email Verification";
        String text = "Please click the following link to verify your email address: " + verificationLink;

        // Create the email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        // Send the email
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            // Handle the exception as needed
        }
    }
}
