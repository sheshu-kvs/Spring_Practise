package com.example.submitemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSerivce {
    
    @Autowired
    private JavaMailSender mailSender;

    public void SendEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        // optionally setFrom if needed: msg.setFrom("yourgmail@gmail.com");
        mailSender.send(msg);
    }
    
}
