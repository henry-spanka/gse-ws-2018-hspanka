package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.console.BoardController;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessGameGUIApplication extends Application implements ChessGameApplication {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("app.fxml"));

        Pane root = fxmlLoader.load();
        BoardController boardController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void run(String... args) throws ApplicationErrorException {
        launch(args);
    }
}
