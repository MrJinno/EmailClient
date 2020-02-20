package com.mrJinno;

import com.mrJinno.controller.services.FetchFoldersService;
import com.mrJinno.controller.services.FolderUpdateService;
import com.mrJinno.model.EmailAccount;
import com.mrJinno.model.EmailMessage;
import com.mrJinno.model.EmailTreeItem;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class EmailManager {
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");
    private List<Folder> folderList = new ArrayList<>();
    private FolderUpdateService folderUpdateService;
    private EmailMessage selectedMessage;
    private EmailTreeItem<String> selectedFolder;

    public EmailManager() {
        folderUpdateService = new FolderUpdateService(folderList);
        folderUpdateService.start();
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, folderList);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }

    public void setMessageAsRead() {
        if (!selectedMessage.isRead()) {
            try {
                selectedMessage.setRead(true);
                selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
                selectedFolder.decrementMessagesCount();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFoldersRoot(EmailTreeItem<String> foldersRoot) {
        this.foldersRoot = foldersRoot;
    }

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public EmailMessage getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessage selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailTreeItem<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }
}
