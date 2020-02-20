package com.mrJinno.controller.services;

import com.mrJinno.controller.EmailSendingResult;
import com.mrJinno.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmailSendService extends Service<EmailSendingResult> {
    private EmailAccount emailAccount;
    private String Subject;
    private String recipient;
    private String content;

    public EmailSendService(EmailAccount emailAccount, String subject, String recipient, String content) {
        this.emailAccount = emailAccount;
        Subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    protected Task<EmailSendingResult> createTask() {
        return null;
    }


}
