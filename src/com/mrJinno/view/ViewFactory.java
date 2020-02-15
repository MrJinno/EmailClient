package com.mrJinno.view;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.BaseController;
import com.mrJinno.controller.LoginWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }
    public void showLoginWindow(){
        Parent parent= loadFxmlFileAndSetController();
        Scene scene= new Scene(parent);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();


    }
    public Parent loadFxmlFileAndSetController(){
        BaseController controller= new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource(controller.getFxmlFile()));
        fxmlLoader.setController(controller);
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
