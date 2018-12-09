package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.gui.AbstractGuiController;
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
     * The primary stage from JavaFX.
     */
    private Stage stage;

    /**
     * Set's a scene.
     * @param scene The scene to be set.
     */
    private void setScene(final Scene scene) {
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
    public void start(final Stage primaryStage) throws IOException {
        stage = primaryStage;

        loadView("app");
    }

    /**
     * Launches JavaFX.
     */
    @Override
    public void run(final String... args) {
        launch(args);
    }

    /**
     * Loads a view into the primary stage.
     * @param viewName The view name.
     * @return The controller used by the new.
     * @throws IOException If the controller cannot be found.
     */
    @SuppressWarnings("PMD.UseProperClassLoader")
    public AbstractGuiController loadView(final String viewName) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(viewName + ".fxml"));

        final Pane root = fxmlLoader.load();
        final AbstractGuiController controller = fxmlLoader.getController();
        controller.setApp(this);

        final Scene scene = new Scene(root);

        setScene(scene);

        return controller;
    }
}
