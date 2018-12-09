package de.techfak.gse.hspanka.controller.console;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.view.console.BoardView;

/**
 * Controller for interacting with the Chess Board.
 */
public class BoardController {
    /**
     * The board model.
     */
    private final Board board;
    /**
     * The board view.
     */
    private final BoardView boardView;

    /**
     * Construct the board controller and setup the dependencies.
     */
    public BoardController() {
        board = new Board();
        boardView = new BoardView();
    }

    /**
     * Instructs the view to display the current board configuration.
     */
    public void showCurrentBoard() {
        boardView.showCurrentConfiguration(board.getConfiguration(), board.getPlayer());
    }

    /**
     * Sets the board to the default configuration.
     *
     * @throws InvalidBoardConfiguration        The configuration can not be parsed.
     * @throws EmptyBoardConfigurationException An empty string was supplied.
     * @see <a href="https://en.wikipedia.org/wiki/Chess#Movement">Chess Movement</a>
     */
    public void setDefaultBoardConfiguration() throws InvalidBoardConfiguration, EmptyBoardConfigurationException {
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
    public void setBoardConfigurationFromString(final String conf) throws
        InvalidBoardConfiguration,
        EmptyBoardConfigurationException {
        FenParser fen = new FenParser(board);

        fen.parse(conf);
    }

    /**
     * Tries to make a move.
     *
     * @param move The move that should be made.
     * @throws ApplicationMoveException If the move is invalid or can not be made.
     */
    public void makeMove(final Move move) throws ApplicationMoveException {
        move.validateUniqueness();
        board.validateMove(move);
        board.executeMove(move);
    }
}
