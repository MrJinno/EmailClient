package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.view.ViewFactory;

public abstract class Controller {
    protected EmailManager emailManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public Controller(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlFile() {
        return fxmlName;
    }
}
