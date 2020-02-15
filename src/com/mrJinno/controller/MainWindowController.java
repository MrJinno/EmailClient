package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
public class MainWindowController extends Controller {
        public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
                super(emailManager, viewFactory, fxmlName);
        }

        @FXML
        private TreeView<?> emailsTreeView;

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

    }
