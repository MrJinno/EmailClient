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
import java.util.ArrayList;

public class ViewFactory {
    private EmailManager emailManager;
    private Stage stage= new Stage();
    private ArrayList<Stage> activeStages;
    private ColorTheme colorTheme= ColorTheme.DEFAULT;

    private FontSize fontSize=FontSize.MEDIUM;

    public ViewFactory(EmailManager emailManager) {
        activeStages=new ArrayList<>();
        this.emailManager = emailManager;
    }

    public void showLoginWindow(){
        System.out.println("Show Login Window Called");
        initializeScene(new LoginWindowController(emailManager, this, "LoginWindow.fxml"),true);
    }

    public void showMainWindow(){
        System.out.println("Show Main Window Called");
        initializeScene(new MainWindowController(emailManager, this, "MainWindow.fxml"), false);
    }

    public void showOptionsWindow(){
        System.out.println("Options window called");
        initializeScene(new OptionsWindowController(emailManager, this, "OptionsWindow.fxml"), true);
    }

    public void initializeScene(Controller controller, boolean isCreatingNewWindow){
        FXMLLoader fxmlLoader= initializeFxmlFile(controller);
        Parent parent=getFxmlRoot(fxmlLoader);
        createScene(parent, isCreatingNewWindow);
        updateStyles();
    }
    public void createScene(Parent parent, boolean isCreatingNewWindow){
        if (isCreatingNewWindow){
            Stage tempStage = new Stage();
            setScene(tempStage, parent);
            activeStages.add(tempStage);
        }else {
            setScene( stage, parent);
            activeStages.add(stage);
        }
    }

    public FXMLLoader initializeFxmlFile(Controller controller){
       FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource(controller.getFxmlFile()));
       fxmlLoader.setController(controller);
        return fxmlLoader;
    }

    public Parent getFxmlRoot(FXMLLoader fxmlLoader){
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeStage(Stage stage){
        stage.close();
        activeStages.remove(stage);
    }
    public void updateStyles() {
        for (Stage stage: activeStages){
            Scene scene=stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }

    public void setScene(Stage stage, Parent parent){
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }
    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }
}
