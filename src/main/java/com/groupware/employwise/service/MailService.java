package com.groupware.employwise.service;

import jakarta.mail.MessagingException;

public interface MailService {
    public void sendEmail(String to, String subject, String body) throws MessagingException;

}