package com.ex.mailservice.services;

import com.ex.mailservice.entities.EmailDetails;
import com.ex.mailservice.entities.MailControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Annotation
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public MailControllerResponse sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);

            return new MailControllerResponse("Mail sent", 200);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            //TODO: specify
            return new MailControllerResponse("Error sending mail: " + e.getMessage(), 500);
        }
    }
}