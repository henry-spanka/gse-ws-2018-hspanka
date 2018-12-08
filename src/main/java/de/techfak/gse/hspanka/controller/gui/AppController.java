package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.ChessGame;
import de.techfak.gse.hspanka.app.ChessGameGUIApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppController {
    private ChessGameGUIApplication app;

    @FXML
    private Button startDefaultGameButton;

    public void startDefaultGame() throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("board.fxml"));

        Pane root = fxmlLoader.load();
        BoardController boardController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        app.setScene(scene);
    }

    public void setApp(ChessGameGUIApplication app) {
        this.app = app;
    }
}
