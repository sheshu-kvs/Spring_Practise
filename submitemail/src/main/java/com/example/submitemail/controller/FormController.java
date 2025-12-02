package com.example.submitemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.submitemail.model.FormData;
import com.example.submitemail.service.EmailSerivce;

@Controller
public class FormController {

    @Autowired
    private EmailSerivce emailService;

    @GetMapping("/")
    public String showForm() {
        return "form";   // it will move to the templates/form.html
    }

    @PostMapping("/submit")
    public String submitForm(FormData data) {
        // Build the email body
        String body = "New form submission:\n\n" +
                      "Name: " + data.getName() + "\n" +
                      "Address: " + data.getAddress() + "\n" +
                      "Message: " + data.getMessage();

        // this recepient we need to mention the email here(sending address)
        String recipient = "info@terrawebs.com"; 
        emailService.SendEmail(recipient, "New Form Submission", body);

        // it will go the success.html
        return "success";
    }
}
