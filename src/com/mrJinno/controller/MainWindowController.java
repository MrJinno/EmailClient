package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
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
                     

        }

        private void setUpEmailsTreeView() {
                emailsTreeView.setRoot(emailManager.getFoldersRoot());
                emailsTreeView.setShowRoot(false);
        }
}
