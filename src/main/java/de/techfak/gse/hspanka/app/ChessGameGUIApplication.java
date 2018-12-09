package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.gui.AbstractGuiController;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The GUI Application.
 */
public class ChessGameGUIApplication extends Application implements ChessGameApplication {
    /**
     * The primary stage from JavaFX
     */
    private Stage stage;

    /**
     * Set's a scene.
     * @param scene The scene to be set.
     */
    public void setScene(Scene scene) {
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Get the primary stage.
     * @return The primary stage.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Initializes the stage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        loadView("app");
    }

    /**
     * Launches JavaFX.
     */
    @Override
    public void run(String... args) {
        launch(args);
    }

    /**
     * Loads a view into the primary stage.
     * @param viewName The view name.
     * @return The controller used by the new.
     * @throws IOException If the controller cannot be found.
     */
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
