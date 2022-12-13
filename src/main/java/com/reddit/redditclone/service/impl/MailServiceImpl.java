package com.reddit.redditclone.service.impl;


import com.reddit.redditclone.exceptions.SpringRedditException;
import com.reddit.redditclone.model.NotificationEmail;
import com.reddit.redditclone.service.MailContentBuilder;
import com.reddit.redditclone.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("2ed9351686-85e963@inbox.mailtrap.io");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        }
        catch (MailException e){
            throw new SpringRedditException("Exception occurred when sending mail to "+ notificationEmail.getRecipient());
        }
    }
}
