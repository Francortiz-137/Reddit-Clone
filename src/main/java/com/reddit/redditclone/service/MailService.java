package com.reddit.redditclone.service;


import com.reddit.redditclone.model.NotificationEmail;
import org.springframework.stereotype.Service;


public interface MailService {
    void sendMail(NotificationEmail notificationEmail);
}
