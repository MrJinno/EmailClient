package com.mrJinno.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import java.util.List;

public class FolderUpdateService extends Service {
    private List<Folder> folderList;
    private static final boolean PROGRAM_IS_RUNNING = true;

    public FolderUpdateService(List<Folder> folderList) {
        this.folderList = folderList;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                updateMessages();
                return null;
            }
        };
    }

    private void updateMessages() throws InterruptedException, MessagingException {
        while (PROGRAM_IS_RUNNING) {
            Thread.sleep(5000);
            for (Folder folder : folderList) {
                if (folder.getType() != Folder.HOLDS_FOLDERS && folder.isOpen()) {
                    folder.getMessageCount();
                }
            }
        }
    }
}
