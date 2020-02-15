package com.mrJinno.view;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.Controller;
import com.mrJinno.controller.LoginWindowController;
import com.mrJinno.controller.MainWindowController;
import com.mrJinno.controller.OptionsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private EmailManager emailManager;
     private FXMLLoader fxmlLoader;
    private Stage stage= new Stage();

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }
    public void showLoginWindow(){
        System.out.println("Show Login Window Called");
        initializeScene(new LoginWindowController(emailManager, this, "LoginWindow.fxml"),false);
    }
    public void showMainWindow(){
        System.out.println("Show Main Window Called");
        initializeScene(new MainWindowController(emailManager, this, "MainWindow.fxml"), false);
    }
    public void showOptionsWindow(){
        System.out.println("Options window called");
        initializeScene(new OptionsWindowController(emailManager, this, "OptionsWindow.fxml"), false);
    }
    public void initializeScene(Controller controller, boolean createNewWindow){
        Parent parent= manageFxmlObject(controller);
        if (createNewWindow){
        Stage tempStage=new Stage();
        setScene(tempStage, parent);
        }else {
            setScene( stage, parent);
        }
    }
    public Parent manageFxmlObject(Controller controller){
        fxmlLoader= new FXMLLoader(getClass().getResource(controller.getFxmlFile()));
        fxmlLoader.setController(controller);
       return getFxmlRoot(fxmlLoader);
    }
    public Parent getFxmlRoot(FXMLLoader fxmlLoader){
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setScene(Stage stage, Parent parent){
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
