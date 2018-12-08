package de.techfak.gse.hspanka.controller.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AppController extends AbstractGuiController {
    @FXML
    private Button startDefaultGameButton;

    public void startDefaultGame() throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("board.fxml"));

        Pane root = fxmlLoader.load();
        BoardController boardController = fxmlLoader.getController();
        boardController.setApp(app);

        Scene scene = new Scene(root);
        app.setScene(scene);

        boardController.setDefaultConfiguration();
    }
}
