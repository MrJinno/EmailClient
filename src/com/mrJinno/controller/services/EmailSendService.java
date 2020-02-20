package com.mrJinno.controller.services;

import com.mrJinno.controller.EmailSendingResult;
import com.mrJinno.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSendService extends Service<EmailSendingResult> {
    private EmailAccount emailAccount;
    private String Subject;
    private String recipient;
    private String content;
    private MimeMessage mimeMessage;

    public EmailSendService(EmailAccount emailAccount, String subject, String recipient, String content) {
        this.emailAccount = emailAccount;
        Subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    protected Task<EmailSendingResult> createTask() {
        return new Task<EmailSendingResult>() {
            @Override
            protected EmailSendingResult call() throws Exception {
                return SendAnEmail();
            }
        };
    }

    private EmailSendingResult SendAnEmail() {
        try {
            createTheMessage();
            setTheContent();
            sendTheMessageAction();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void createTheMessage() throws MessagingException {
        mimeMessage= new MimeMessage(emailAccount.getSession());
        mimeMessage.setFrom(emailAccount.getAddress());
        mimeMessage.addRecipients(Message.RecipientType.TO, recipient);
    }

    private void setTheContent() throws MessagingException {
        Multipart multipart=new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");
        multipart.addBodyPart(messageBodyPart);
        mimeMessage.setContent(multipart);
    }

    private void sendTheMessageAction() throws NoSuchProviderException {
        Transport transport = emailAccount.getSession().getTransport();
        
    }


}
