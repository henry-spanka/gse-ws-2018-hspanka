package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.FileIO;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.view.gui.Alert;
import de.techfak.gse.hspanka.view.gui.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;

public class AppController extends AbstractGuiController {
    @FXML
    private Button startDefaultGameButton;

    public void startDefaultGame(ActionEvent event) throws IOException, ApplicationErrorException {
        event.consume();

        BoardController boardController = (BoardController) app.loadView("board");
        boardController.setDefaultConfiguration();
    }

    @FXML
    public void loadGame(ActionEvent event) throws IOException, ApplicationErrorException {
        event.consume();

        FileChooser fileChooser = new FileChooser(app.getStage());
        File file = fileChooser.openFile();

        FileIO fileIO = new FileIO(file);
        String fen = fileIO.read();

        BoardController boardController = (BoardController) app.loadView("board");
        try {
            boardController.setBoardConfigurationFromString(fen);
        } catch (ApplicationErrorException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.loadError();
            alert.show();
        }
    }
}
