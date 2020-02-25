package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.services.LoginService;
import com.mrJinno.model.EmailAccount;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends Controller implements Initializable {

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAddressField.setText("bio.sad7@gmail.com");
        setUpKeyEventHandler();
    }

    private void setUpKeyEventHandler() {
        passwordField.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            switch (e.getCode()) {
                case ENTER:
                    loginButtonAction();
                    break;
                case ESCAPE:
                    System.exit(0);
            }
        });
    }

    @FXML
    private TextField emailAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void loginButtonAction() {
        if (fieldsAreValid()) {
            initializeLoginService();
        }
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please Insert Email");
            return false;
        }
        if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Please insert Password");
            return false;
        }
        return true;
    }

    private void initializeLoginService() {
        EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
        LoginService loginService = new LoginService(emailAccount, emailManager);
        loginService.start();
        loginService.setOnSucceeded(event -> {
            EmailLoginResult emailLoginResult = loginService.getValue();
            switch (emailLoginResult) {
                case SUCCESS:
                    emailManager.addEmailAccount(emailAccount);
                    switchStageToMainWindow();
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    errorLabel.setText("unexpected error!!!");
                    break;
                case FAILED_BY_CREDENTIALS:
                    errorLabel.setText("Invalid credentials!");
                    break;
            }
        });
    }

    private void switchStageToMainWindow() {
        System.out.println("Login Success!");
        viewFactory.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
