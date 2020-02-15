package com.mrJinno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent parent= FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
        Scene scene=new Scene(parent, 500, 400);
        mainStage.setTitle("Hello World");
        mainStage.setScene(scene);
        mainStage.show();
    }
}
