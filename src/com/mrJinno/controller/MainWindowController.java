package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.model.EmailMessage;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {
        public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
                super(emailManager, viewFactory, fxmlName);
        }

        @FXML
        private TreeView<String> emailsTreeView;

        @FXML
        private TableView<?> emailsTableView;

        @FXML
        private WebView emailWebView;
        @FXML
        private TableColumn<EmailMessage, String> senderColumn;

        @FXML
        private TableColumn<EmailMessage, String> subjectColumn;

        @FXML
        private TableColumn<EmailMessage, String> recipientColumn;

        @FXML
        private TableColumn<EmailMessage, Integer> sizeColumn;

        @FXML
        private TableColumn<EmailMessage, Date> dateColumn;
        @FXML
        void addAccountAction() {
          viewFactory.showLoginWindow();
        }
        @FXML
        void optionsAction() {
        viewFactory.showOptionsWindow();
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
             setUpEmailsTreeView();
             setUpEmailTableColumns();
                     

        }

        private void setUpEmailTableColumns() {
                senderColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, String>("sender"));
        }

        private void setUpEmailsTreeView() {
                emailsTreeView.setRoot(emailManager.getFoldersRoot());
                emailsTreeView.setShowRoot(false);
        }
}
