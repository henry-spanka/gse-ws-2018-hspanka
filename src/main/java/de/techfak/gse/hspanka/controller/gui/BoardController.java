package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.view.gui.BoardPane;
import javafx.fxml.FXML;

public class BoardController extends AbstractGuiController {
    @FXML
    public BoardPane grid;

    /**
     * The board model.
     */
    private final Board board;

    public BoardController() {
        this.board = new Board();
    }

    @FXML
    public void initialize() {
        grid.initWithSize(Board.FIELD_SIZE);
    }

    public void setDefaultConfiguration() {
        // TODO: IMPLEMENT
    }
}
