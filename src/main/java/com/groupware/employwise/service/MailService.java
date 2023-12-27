package com.groupware.employwise.service;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface MailService {
    public void sendEmail(String to, String subject, String body, String templateName, Context context);

}
