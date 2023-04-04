package com.zeroone.service;

import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    private final String EMAIL_FROM = "example@example.com";

    public EmailSenderService(JavaMailSender mailSender) {
        this.javaMailSender = mailSender;
    }

        @Async
        public void sendEmail(String toEmail, String subject, String body) {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom(EMAIL_FROM);
                messageHelper.setTo(toEmail);
                messageHelper.setSubject(subject);
                messageHelper.setText(body);
            };
            javaMailSender.send(messagePreparator);
        }


        @Async
        public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) {
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom(EMAIL_FROM);
                messageHelper.setTo(toEmail);
                messageHelper.setSubject(subject);
                messageHelper.setText(body);
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(attachmentPath));
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(attachmentPart);
                mimeMessage.setContent(multipart);
            };
            javaMailSender.send(messagePreparator);
        }

}
