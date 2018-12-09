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

public class BoardController extends AbstractGuiController implements Observer {
    /**
     * The board model.
     */
    private final Board board;

    /**
     * The Board grid.
     */
    @FXML
    public BoardPane grid;

    /**
     * The current player text.
     */
    @FXML
    public CurrentPlayerText currentPlayer;

    public BoardController() {
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
        FenParser fen = new FenParser(board);

        fen.parse(conf);
    }

    /**
     * Called by the observervable to update the view.
     * @param o The observable.
     * @param arg The board if update is forced.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o != null && o.equals(this.board)) {
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
    public void fieldClicked(int col, int row) {
        Move move = this.board.getMove();

        Move staged_move;

        // If no selection has been made yet, try set selection as source positition.
        if (move == null) {
            staged_move = new Move(col, row, Move.POS_UNKNOWN, Move.POS_UNKNOWN);
            try {
                this.board.validateMove(staged_move);
            } catch (ApplicationMoveException e) {
                return;
            }
        // If the col and row is present in the move, we remove the source position to deselect.
        } else if (move.isInvolved(col, row)) {
            staged_move = new Move(Move.POS_UNKNOWN, Move.POS_UNKNOWN, move.getcTo(), move.getrTo());
        } else {
        // Try to set position as source position and if it fails try destination instead.
            staged_move = new Move(col, row, move.getcTo(), move.getrTo());
            try {
                // Check if the position is a valid source position.
                this.board.validateMove(staged_move, true);
            } catch (ApplicationMoveException e) {
                staged_move = new Move(move.getcFrom(), move.getrFrom(), col, row);
                try {
                    // Try as destination position instead.
                    this.board.validateMove(staged_move, true);
                } catch (ApplicationMoveException e3) {
                    return;
                }
            }
        }

        // Set's the move on the board.
        this.board.setMove(staged_move);

        // If source and destionation is present, we should execute the move.
        if (staged_move.sourceComplete() && staged_move.destinationComplete()) {
            board.executeMove();
        }
    }

    /**
     * Saves the game to a file. Called by JavaFX when the button is selected.
     * @param event The ActionEvent.
     */
    @FXML
    public void saveGame(ActionEvent event) {
        event.consume();

        // Open the FileChooser.
        FileChooser fileChooser = new FileChooser(app.getStage());
        File fileName = fileChooser.saveFile();

        // If the fileName is null show an error message.
        if (fileName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.saveError();
            alert.show();
            return;
        }

        // Convert the configuration into a string.
        FenParser fenParser = new FenParser(null);
        String data = fenParser.toString(board.getConfiguration(), board.getPlayer());

        // Try to write to the selected file. If it fails show an error message instead.
        FileIO fileIO = new FileIO(fileName);
        if (fileIO.write(data) == false) {
            if (fileName == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.saveError();
                alert.show();
            }
        }
    }

    /**
     * Switches the view back to the menu (AppController).
     * @param event The ActionEvent.
     * @throws IOException Thrown if the view cannot be found.
     */
    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        event.consume();

        app.loadView("app");
    }
}
