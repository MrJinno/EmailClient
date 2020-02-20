package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.services.EmailSendService;
import com.mrJinno.model.EmailAccount;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ComposeMessageController extends Controller implements Initializable {
    public ComposeMessageController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private TextField recipientTextField;

    @FXML
    private ChoiceBox<EmailAccount> emailAccountChoice;

    @FXML
    private TextField subjectTextField;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private Label errorLabel;

    @FXML
    void sendButtonAction() {
      startEmailSendingService();
    }

    private void startEmailSendingService() {
        EmailSendService emailSendService= new EmailSendService(
                emailAccountChoice.getValue(),
                subjectTextField.getText(),
                recipientTextField.getText(),
                htmlEditor.getHtmlText()
        );
        emailSendService.start();
        emailSendService.setOnSucceeded(e->{
            EmailSendingResult emailSendingResult= emailSendService.getValue();
            switch (emailSendingResult){
                case SUCCESS:
                    viewFactory.closeStage((Stage)errorLabel.getScene().getWindow());
                    break;
                case FAILED_BY_PROVIDER:
                    errorLabel.setText("Provider error!");
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    errorLabel.setText("Unexpected Error!");
                    break;
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailAccountChoice.setItems(emailManager.getEmailAccounts());
        emailAccountChoice.setValue(emailManager.getEmailAccounts().get(0));
    }
}


