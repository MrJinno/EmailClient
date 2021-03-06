package com.mrJinno.view;

import com.mrJinno.EmailManager;
import com.mrJinno.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private EmailManager emailManager;
    private Stage stage = new Stage();
    private ArrayList<Stage> activeStages;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.SMALL;

    public ViewFactory(EmailManager emailManager) {
        activeStages = new ArrayList<>();
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
        System.out.println("Show Login Window Called");
        LoginWindowController loginWindowController = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeScene(loginWindowController, true);
    }

    public void showMainWindow() {
        System.out.println("Show Main Window Called");
        MainWindowController mainWindowController = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initializeScene(mainWindowController, false);
    }

    public void showOptionsWindow() {
        System.out.println("Options window called");
        OptionsWindowController optionsWindowController = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeScene(optionsWindowController, true);
    }

    public void showComposeMessageWindow() {
        System.out.println("Compose window called");
        ComposeMessageController composeMessageController = new ComposeMessageController(emailManager, this, "ComposeMessageWindow.fxml");
        initializeScene(composeMessageController, true);
    }

    private void initializeScene(Controller controller, boolean isCreatingNewWindow) {
        FXMLLoader fxmlLoader = initializeFxmlFile(controller);
        Parent parent = getFxmlRoot(fxmlLoader);
        createScene(parent, isCreatingNewWindow);
        updateStyles();
    }

    private void createScene(Parent parent, boolean isCreatingNewWindow) {
        if (isCreatingNewWindow) {
            setScene(new Stage(), parent);
        } else {
            setScene(stage, parent);
        }
    }

    private FXMLLoader initializeFxmlFile(Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlFile()));
        fxmlLoader.setController(controller);
        return fxmlLoader;
    }

    private Parent getFxmlRoot(FXMLLoader fxmlLoader) {
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeStage(Stage stage) {
        stage.close();
        activeStages.remove(stage);
    }

    public void updateStyles() {
        for (Stage stage : activeStages) {
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }

    private void setScene(Stage stage, Parent parent) {
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
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
