package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessGameGUIApplication extends Application implements ChessGameApplication {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new VBox());
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void run(String... args) throws ApplicationErrorException {
        launch(args);
    }
}
