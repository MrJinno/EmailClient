package com.mrJinno.controller;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.services.MessageRenderService;
import com.mrJinno.model.EmailMessage;
import com.mrJinno.model.EmailTreeItem;
import com.mrJinno.model.SizeInteger;
import com.mrJinno.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends Controller implements Initializable {

    private MessageRenderService messageRenderService;
    private MenuItem markUnreadMenuItem = new MenuItem("mark as unread");
    private MenuItem deleteMessageMenuItem = new MenuItem("delete message");

    public MainWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private TreeView<String> emailsTreeView;

    @FXML
    private TableView<EmailMessage> emailsTableView;

    @FXML
    private WebView emailWebView;
    @FXML
    private TableColumn<EmailMessage, String> senderColumn;

    @FXML
    private TableColumn<EmailMessage, String> subjectColumn;

    @FXML
    private TableColumn<EmailMessage, String> recipientColumn;

    @FXML
    private TableColumn<EmailMessage, SizeInteger> sizeColumn;

    @FXML
    private TableColumn<EmailMessage, Date> dateColumn;

    @FXML
    void WriteEmailAction() {
        viewFactory.showComposeMessageWindow();
    }

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
        setUpEmailTableView();
        setUpSelectedFolder();
        setUpBoldRows();
        setUpMessageRenderService();
        setUpMessageSelection();
        setUpTableViewContextMenu();
    }

    private void setUpEmailsTreeView() {
        emailsTreeView.setRoot(emailManager.getFoldersRoot());
        emailsTreeView.setShowRoot(false);
    }

    private void setUpEmailTableView() {
        senderColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, String>("sender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, String>("subject"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, String>("recipient"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, SizeInteger>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<EmailMessage, Date>("date"));

    }

    private void setUpSelectedFolder() {
        emailsTreeView.setOnMouseClicked(e -> {
            EmailTreeItem<String> item = (EmailTreeItem<String>) emailsTreeView.getSelectionModel().getSelectedItem();
            if (item != null) {
                emailManager.setSelectedFolder(item);
                emailsTableView.setItems(item.getEmailMessages());
            }
        });
    }

    private void setUpBoldRows() {
        emailsTableView.setRowFactory(new Callback<TableView<EmailMessage>, TableRow<EmailMessage>>() {
            @Override
            public TableRow<EmailMessage> call(TableView<EmailMessage> param) {
                return new TableRow<EmailMessage>() {
                    @Override
                    protected void updateItem(EmailMessage emailMessage, boolean empty) {
                        super.updateItem(emailMessage, empty);
                        if (emailMessage != null) {
                            if (emailMessage.isRead()) {
                                setStyle("");
                            } else {
                                setStyle("-fx-font-weight: bold");
                            }
                        }
                    }

                };
            }
        });
    }

    private void setUpMessageRenderService() {
        messageRenderService = new MessageRenderService(emailWebView.getEngine());
    }

    private void setUpMessageSelection() {
        emailsTableView.setOnMouseClicked(e -> {
            EmailMessage emailMessage = emailsTableView.getSelectionModel().getSelectedItem();
            if (emailMessage != null) {
                emailManager.setSelectedMessage(emailMessage);
                messageRenderService.setEmailMessage(emailMessage);
                messageRenderService.restart();
                emailManager.setMessageReadState(true);
            }
        });
    }

    private void setUpTableViewContextMenu() {
        emailsTableView.setContextMenu(new ContextMenu(markUnreadMenuItem, deleteMessageMenuItem));
        markUnreadMenuItem.setOnAction(e -> emailManager.setMessageReadState(false));
        deleteMessageMenuItem.setOnAction(e -> {
            emailManager.deleteSelectedMessage();
            emailWebView.getEngine().loadContent("");
        });
    }
}
