package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.exceptions.PieceNotOwnedException;
import de.techfak.gse.hspanka.view.gui.BoardPane;
import de.techfak.gse.hspanka.view.gui.CurrentPlayerText;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.Observable;
import java.util.Observer;

public class BoardController extends AbstractGuiController implements Observer {
    @FXML
    public BoardPane grid;

    @FXML
    public CurrentPlayerText currentPlayer;

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
            grid.initialize(this);
            // We can only observe after we have initialized the grid
            // We make sure the view is at least once up to date.
            board.addObserver(this);
            this.update(null, this.board);
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

    @Override
    public void update(Observable o, Object arg) {
        if (o != null && o.equals(this.board)) {
            grid.redraw(this.board.getConfiguration());
            currentPlayer.setPlayer(this.board.getPlayer());
        } else if (arg != null && arg.equals(this.board)) {
            // Force update by controller.
            grid.redraw(this.board.getConfiguration());
            currentPlayer.setPlayer(this.board.getPlayer());
        }
    }

    @FXML
    public void fieldClicked(int col, int row) {
        Move move = this.board.getMove();

        if (move == null) {
            move = new Move(col, row, Move.POS_UNKNOWN, Move.POS_UNKNOWN);
            try {
                this.board.validateMove(move);
            } catch (ApplicationMoveException e) {
                return;
            }
            
            this.board.setMove(move);
        } else if (!move.sourceComplete()) {
            move = new Move(col, row, move.getcTo(), move.getrTo());
            try {
                this.board.validateMove(move);
            } catch (ApplicationMoveException e) {
                return;
            }

            this.board.setMove(move);
        } else if (!move.destinationComplete()) {
            move = new Move(move.getcFrom(), move.getrFrom(), col, row);
            this.board.setMove(move);
        }

        if (move.sourceComplete() && move.destinationComplete()) {
            board.executeMove();
        }
    }
}
