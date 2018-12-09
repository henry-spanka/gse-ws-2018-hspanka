package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.scene.Scene;

/**
 * A interface for interacting with the application.
 */
public interface ChessGameApplication {
    /**
     * Starts the application.
     * @param args The command line parameters passed on execution.
     * @throws ApplicationErrorException
     */
    void run(String... args) throws ApplicationErrorException;

    /**
     * Switches the scene.
     * @param scene The scene to be set.
     */
    void setScene(Scene scene);
}
