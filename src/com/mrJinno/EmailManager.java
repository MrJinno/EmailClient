package com.mrJinno;

import com.mrJinno.controller.services.FetchFoldersService;
import com.mrJinno.model.EmailAccount;
import com.mrJinno.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManager {
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");


    public void addEmailAccount(EmailAccount emailAccount){
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        FetchFoldersService fetchFoldersService= new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
        foldersRoot.getChildren().add(treeItem);
    }

    public void setFoldersRoot(EmailTreeItem<String> foldersRoot) {
        this.foldersRoot = foldersRoot;
    }

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }
}
