package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.gui.AbstractGuiController;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessGameGUIApplication extends Application implements ChessGameApplication {
    private Stage stage;

    public void setScene(Scene scene) {
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        loadView("app");
    }

    @Override
    public void run(String... args) throws ApplicationErrorException {
        launch(args);
    }

    public AbstractGuiController loadView(String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(viewName + ".fxml"));

        Pane root = fxmlLoader.load();
        AbstractGuiController controller = fxmlLoader.getController();
        controller.setApp(this);

        Scene scene = new Scene(root);

        setScene(scene);

        return controller;
    }
}
