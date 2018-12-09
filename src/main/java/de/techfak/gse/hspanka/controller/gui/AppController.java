package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.view.gui.FileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class AppController extends AbstractGuiController {
    @FXML
    private Button startDefaultGameButton;

    public void startDefaultGame() throws IOException, ApplicationErrorException {
        BoardController boardController = (BoardController) app.loadView("board");

        boardController.setDefaultConfiguration();
    }

    @FXML
    public void loadGame(ActionEvent event) {
        event.consume();

        FileChooser fileChooser = new FileChooser(app.getStage());
        File fileName = fileChooser.openFile();
    }
}
