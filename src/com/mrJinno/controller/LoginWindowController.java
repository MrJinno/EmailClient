package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController extends Controller {
    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private TextField emailAdressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    void loginButtonAction() {
        viewFactory.showMainWindow();
        System.out.println("Click!");
        viewFactory.closeStage((Stage)errorLabel.getScene().getWindow());
    }
}
