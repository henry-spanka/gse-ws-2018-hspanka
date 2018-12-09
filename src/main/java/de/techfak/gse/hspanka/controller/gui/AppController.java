package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.FileIO;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.view.gui.Alert;
import de.techfak.gse.hspanka.view.gui.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;

/**
 * Responible for the GUI Menu.
 */
public class AppController extends AbstractGuiController {
    /**
     * Starts a new default game.
     * @param event The ActionEvent.
     * @throws IOException Thrown if the view cannot be found.
     * @throws ApplicationErrorException Thrown if the configuration cannot be parsed.
     */
    @FXML
    public void startDefaultGame(final ActionEvent event) throws IOException, ApplicationErrorException {
        event.consume();

        final BoardController boardController = (BoardController) app.loadView("board");
        boardController.setDefaultConfiguration();
    }

    /**
     * Load sa game from File.
     * @param event The ActionEvent.
     * @throws IOException Thrown if the file cannot be found.
     * @throws ApplicationErrorException Thrown if the content cannot be parsed.
     */
    @FXML
    public void loadGame(final ActionEvent event) throws IOException, ApplicationErrorException {
        event.consume();

        final FileChooser fileChooser = new FileChooser(app.getStage());
        final File file = fileChooser.openFile();

        final FileIO fileIO = new FileIO(file);
        final String fen = fileIO.read();

        final BoardController boardController = (BoardController) app.loadView("board");
        try {
            boardController.setBoardConfigurationFromString(fen);
        } catch (ApplicationErrorException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.loadError();
            alert.show();
        }
    }
}
