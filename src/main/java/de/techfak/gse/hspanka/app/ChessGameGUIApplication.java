package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.gui.AppController;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("app.fxml"));

        Pane root = fxmlLoader.load();
        AppController appController = fxmlLoader.getController();
        appController.setApp(this);

        Scene scene = new Scene(root);

        setScene(scene);
    }

    @Override
    public void run(String... args) throws ApplicationErrorException {
        launch(args);
    }
}
