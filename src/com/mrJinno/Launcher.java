package com.mrJinno;

import com.mrJinno.view.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
   private   ViewFactory viewFactory;
    @Override
    public void start(Stage mainStage) throws Exception {
        viewFactory=new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
    }
}
