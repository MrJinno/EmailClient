package com.mrJinno.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailTreeItem<String> extends TreeItem<String> {
    private String name;
    private ObservableList<EmailMessage> emailMessages;
    private int unreadMessagesCount = 0;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }

    private EmailMessage fetchMessage(Message message) throws MessagingException {
        boolean messageIsRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                message.getFrom()[0].toString(),
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                message.getSentDate(),
                messageIsRead,
                message
        );
        if (!messageIsRead) {
            incrementMessagesCount();
        }
        return emailMessage;
    }

    public void addEmail(Message message) throws MessagingException {
        emailMessages.add(fetchMessage(message));
    }

    public void addEmailToTop(Message message) throws MessagingException {
        emailMessages.add(0, fetchMessage(message));
    }

    public void incrementMessagesCount() {
        unreadMessagesCount++;
        updateUnreadMessagesCount();
    }

    public void decrementMessagesCount() {
        unreadMessagesCount--;
        updateUnreadMessagesCount();
    }

    @SuppressWarnings("unchecked")
    private void updateUnreadMessagesCount() {
        if (unreadMessagesCount > 0) {
            this.setValue((String) (name + "[" + unreadMessagesCount + "]"));
        } else {
            this.setValue(name);
        }
    }

    public ObservableList<EmailMessage> getEmailMessages() {
        return emailMessages;
    }
}
