module EmailClient {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens com.mrJinno;
    opens com.mrJinno.view;
    opens com.mrJinno.controller;
}