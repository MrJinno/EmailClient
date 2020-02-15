package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.view.ViewFactory;

public abstract class BaseController {
private EmailManager emailManager;
private ViewFactory viewFactory;
private String fxmlName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
}
