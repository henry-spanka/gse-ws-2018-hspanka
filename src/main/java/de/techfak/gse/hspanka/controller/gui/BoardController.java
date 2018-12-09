package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.FileIO;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.EmptyBoardConfigurationException;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.view.gui.Alert;
import de.techfak.gse.hspanka.view.gui.BoardPane;
import de.techfak.gse.hspanka.view.gui.CurrentPlayerText;
import de.techfak.gse.hspanka.view.gui.FileChooser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * The Board controller responsible for the board.
 */
public class BoardController extends AbstractGuiController implements Observer {
    /**
     * The view name.
     */
    public static final String VIEW_NAME = "board";

    /**
     * The board model.
     */
    private final Board board;

    @FXML
    /**
     * The Board grid.
     */
    private BoardPane grid;

    @FXML
    /**
     * The current player text.
     */
    private CurrentPlayerText currentPlayer;

    /**
     * initializes the BoardController.
     */
    public BoardController() {
        super();

        this.board = new Board();
    }

    /**
     * Initializes the grid.
     */
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
    public void setBoardConfigurationFromString(final String conf) throws
        InvalidBoardConfiguration,
        EmptyBoardConfigurationException {
        final FenParser fen = new FenParser(board);

        fen.parse(conf);
    }

    /**
     * Called by the observervable to update the view.
     * @param observable The observable.
     * @param arg The board if update is forced.
     */
    @Override
    public void update(final Observable observable, final Object arg) {
        if (observable != null && observable.equals(this.board)) {
            grid.redraw(this.board.getConfiguration(), this.board.getMove());
            currentPlayer.setPlayer(this.board.getPlayer());
        } else if (arg != null && arg.equals(this.board)) {
            // Force update by controller.
            grid.redraw(this.board.getConfiguration(), this.board.getMove());
            currentPlayer.setPlayer(this.board.getPlayer());
        }
    }

    /**
     * Called by JavaFX when a field is clicked.
     * @param col The column clicked.
     * @param row The row clicked.
     */
    @FXML
    public void fieldClicked(final int col, final int row) {
        final Move move = this.board.getMove();

        Move stagedMove;

        // If no selection has been made yet, try set selection as source positition.
        if (move == null) {
            stagedMove = new Move(col, row, Move.POS_UNKNOWN, Move.POS_UNKNOWN);
            try {
                this.board.validateMove(stagedMove);
            } catch (ApplicationMoveException e) {
                return;
            }
        // If the col and row is present in the move, we remove the source position to deselect.
        } else if (move.isInvolved(col, row)) {
            stagedMove = new Move(Move.POS_UNKNOWN, Move.POS_UNKNOWN, move.getcTo(), move.getrTo());
        } else {
        // Try to set position as source position and if it fails try destination instead.
            stagedMove = new Move(col, row, move.getcTo(), move.getrTo());
            try {
                // Check if the position is a valid source position.
                this.board.validateMove(stagedMove, true);
            } catch (ApplicationMoveException e) {
                stagedMove = new Move(move.getcFrom(), move.getrFrom(), col, row);
                try {
                    // Try as destination position instead.
                    this.board.validateMove(stagedMove, true);
                } catch (ApplicationMoveException e3) {
                    return;
                }
            }
        }

        // Set's the move on the board.
        this.board.setMove(stagedMove);

        // If source and destionation is present, we should execute the move.
        if (stagedMove.sourceComplete() && stagedMove.destinationComplete()) {
            board.executeMove();
        }
    }

    /**
     * Saves the game to a file. Called by JavaFX when the button is selected.
     * @param event The ActionEvent.
     */
    @FXML
    public void saveGame(final ActionEvent event) {
        event.consume();

        // Open the FileChooser.
        final FileChooser fileChooser = new FileChooser(app.getStage());
        final File fileName = fileChooser.saveFile();

        // If the fileName is null show an error message.
        if (fileName == null) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.saveError();
            alert.show();
            return;
        }

        // Convert the configuration into a string.
        final FenParser fenParser = new FenParser(null);
        final String data = fenParser.toString(board.getConfiguration(), board.getPlayer());

        // Try to write to the selected file. If it fails show an error message instead.
        final FileIO fileIO = new FileIO(fileName);
        if (!fileIO.write(data)) {
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.saveError();
            alert.show();
        }
    }

    /**
     * Switches the view back to the menu (AppController).
     * @param event The ActionEvent.
     * @throws IOException Thrown if the view cannot be found.
     */
    @FXML
    public void backToMenu(final ActionEvent event) throws IOException {
        event.consume();

        app.loadView(AppController.VIEW_NAME);
    }
}
