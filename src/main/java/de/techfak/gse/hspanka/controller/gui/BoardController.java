package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.view.gui.BoardPane;
import javafx.application.Platform;
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
        Platform.runLater(() -> {
            grid.initialize();
            grid.redraw(board.getConfiguration());
        });
    }

    /**
     * Sets the board to the default configuration.
     *
     * @throws InvalidBoardConfiguration        The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void setDefaultConfiguration() throws InvalidBoardConfiguration, EmptyBoardConfigurationException {
        final String conf = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";

        setBoardConfigurationFromString(conf);
    }

    /**
     * Sets the board to the given configuration.
     *
     * @param conf The board configuration to be set in simplified FEN notation
     * @throws InvalidBoardConfiguration        The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public void setBoardConfigurationFromString(final String conf) throws
        InvalidBoardConfiguration,
        EmptyBoardConfigurationException {
        FenParser fen = new FenParser(board);

        fen.parse(conf);
    }
}
