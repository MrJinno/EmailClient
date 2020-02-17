package com.mrJinno;

import com.mrJinno.model.EmailAccount;
import javafx.scene.control.TreeItem;

public class EmailManager {
    private TreeItem<String> foldersRoot = new TreeItem<>("");

    public void setFoldersRoot(TreeItem<String> foldersRoot) {
        this.foldersRoot = foldersRoot;
    }

    public TreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        TreeItem<String> treeItem = new TreeItem<>(emailAccount.getAddress());
        treeItem.setExpanded(true);
        foldersRoot.getChildren().add(treeItem);
    }
}
