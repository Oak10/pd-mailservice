package com.ex.mailservice.controllers;

import com.ex.mailservice.entities.EmailDetails;
import com.ex.mailservice.entities.MailControllerResponse;
import com.ex.mailservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired private
    EmailService emailService;
    @PostMapping("/sendMail")
    public ResponseEntity<MailControllerResponse>
    sendMail(@RequestBody EmailDetails details)
    {
        return new ResponseEntity<>(emailService.sendSimpleMail(details),HttpStatus.OK);
    }

}
