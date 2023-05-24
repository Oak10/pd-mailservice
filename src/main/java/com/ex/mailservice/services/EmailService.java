package com.ex.mailservice.services;

import com.ex.mailservice.entities.EmailDetails;
import com.ex.mailservice.entities.MailControllerResponse;

public interface EmailService {
    MailControllerResponse sendSimpleMail(EmailDetails details);
}
