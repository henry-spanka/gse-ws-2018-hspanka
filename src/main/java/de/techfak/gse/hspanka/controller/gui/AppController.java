package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppController extends AbstractGuiController {
    @FXML
    private Button startDefaultGameButton;

    public void startDefaultGame() throws IOException, ApplicationErrorException {
        BoardController boardController = (BoardController) app.loadView("board");

        boardController.setDefaultConfiguration();
    }
}
