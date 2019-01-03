package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.Player;
import de.techfak.gse.hspanka.controller.gui.BoardController;
import de.techfak.gse.hspanka.piece.Piece;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.Objects;

/**
 * Displays the board on the stage.
 */
public class BoardPane extends GridPane {
    /**
     * The minimum size of the grid pane.
     */
    private static final int MIN_SIZE = 5;

    /**
     * The hightlight color.
     */
    private static final String HIGHLIGHT_COLOR = "#957973";

    /**
     * The BoardController so we can setup the event handler correctly.
     */
    private BoardController controller;

    /**
     * Returns the StackPane at the specified position.
     *
     * @param col The column.
     * @param row The row.
     * @return The StackPane or null if not found.
     */
    private StackPane getStackPane(final int col, final int row) {
        for (final Node node : super.getChildren()) {
            final Integer nodeCol = BoardPane.getColumnIndex(node);
            final Integer nodeRow = BoardPane.getRowIndex(node);

            if (nodeCol != null && nodeRow != null && nodeCol == col && nodeRow == row) {
                return (StackPane) node;
            }
        }

        return null;
    }

    /**
     * Registers an event handler for the given row and column.
     *
     * @param col  The column where the event was fired.
     * @param row  The row where the event was fired.
     * @param pane The pane on which the event handler should be registered.
     */
    private void registerEventHandler(final int col, final int row, final StackPane pane) {
        final EventHandler<MouseEvent> handler = event -> {
            controller.fieldClicked(col, row);
            event.consume();
        };

        pane.setOnMouseClicked(handler);
    }

    /**
     * Set's the color of a field to display the alternating grid colors.
     *
     * @param col  The column where the field is located.
     * @param row  The row where the field is located.
     * @param pane The pane on which the color should be applied.
     */
    private void colorizeField(final int col, final int row, final StackPane pane) {
        colorizeField(col, row, pane, 0);
    }

    /**
     * Set's the color of a field to display the alternating grid colors.
     *
     * @param col  The column where the field is located.
     * @param row  The row where the field is located.
     * @param pane The pane on which the color should be applied.
     * @param alt  Whether we should use alternate colors.
     */
    private void colorizeField(final int col, final int row, final StackPane pane, int alt) {
        if ((row + col) % 2 == 0) {
            if (alt == 1) {
                pane.setStyle("-fx-background-color: #E67E22");
            } else if (alt == 2) {
                pane.setStyle("-fx-background-color: #7D3C98");
            } else {
                pane.setStyle("-fx-background-color: #e8ebef");
            }
        } else {
            if (alt == 1) {
                pane.setStyle("-fx-background-color: #DC7633");
            } else if (alt == 2) {
                pane.setStyle("-fx-background-color: #A569BD");
            } else {
                pane.setStyle("-fx-background-color: #7d8796");
            }
        }
    }

    /**
     * Set's the color of a field to display the alternating grid colors.
     *
     * @param col           The column where the field is located.
     * @param row           The row where the field is located.
     * @param pane          The pane on which the color should be applied.
     * @param allowedFields The allowed fields that should be colored differently.
     * @param board         The board.
     */
    private void colorizeField(
        final int col,
        final int row,
        final StackPane pane,
        boolean[][] allowedFields,
        Piece piece,
        Board board
    ) {
        if (allowedFields[row][col]) {
            if (piece == null) {
                colorizeField(col, row, pane, 1);
            } else if (!piece.getPlayer().equals(board.getPlayer())) {
                colorizeField(col, row, pane, 2);
            } else {
                colorizeField(col, row, pane, 0);
            }
        } else {
            colorizeField(col, row, pane, 0);
        }
    }

    /**
     * Initializes the grid.
     *
     * @param controller The board controller on which the event handler's should be registered.
     */
    public void initialize(final BoardController controller) {
        this.controller = controller;

        // Set the grid size.
        for (int i = 0; i < Board.FIELD_SIZE; i++) {
            getColumnConstraints().add(
                new ColumnConstraints(MIN_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    HPos.CENTER, true
                )
            );
            getRowConstraints().add(
                new RowConstraints(MIN_SIZE, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true
                )
            );
        }

        // Create a square in each cell and color it.
        // Also registers the event handler.
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                final StackPane square = new StackPane();
                colorizeField(col, row, square);

                registerEventHandler(col, row, square);

                add(square, col, row);
            }
        }
    }

    /**
     * Redraw's the grid.
     *
     * @param board The board to be drawn.
     */
    public void redraw(final Board board) {
        Piece[][] pieces = board.getConfiguration();
        Move move = board.getMove();
        boolean[][] allowedFields = board.getConstraintFieldGenerator().getFields();

        for (int row = 0; row < Board.FIELD_SIZE; row++) {

            // Check each piece in a row from the left to the right.
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                final Piece piece = pieces[row][col];

                final StackPane square = getStackPane(col, row);

                // If we have a field without a piece, remove the image (if any) and set color.
                if (piece == null) {
                    if (Objects.requireNonNull(square).getChildren() != null && !square.getChildren().isEmpty()) {
                        square.getChildren().remove(0, 1);
                    }

                    colorizeField(col, row, square, allowedFields, null, board);
                } else {
                    // Check if the field is involved in a move and color it appropriately.
                    if (move != null && move.isInvolved(col, row)) {
                        Objects.requireNonNull(square).setStyle("-fx-background-color: #1e4156");
                    } else {
                        colorizeField(col, row, square, allowedFields, piece, board);
                    }

                    StackPane piecePane;
                    // Add the piece image onto the cell.
                    if (Objects.requireNonNull(square).getChildren() == null || square.getChildren().isEmpty()) {
                        piecePane = new StackPane();
                        square.getChildren().add(piecePane);
                    } else {
                        piecePane = (StackPane) square.getChildren().get(0);
                    }
                    final PieceImage pieceImg = new PieceImage(piece);
                    piecePane.setBackground(pieceImg.asBackground());
                }
            }
        }

        if (board.getPlayerWon() != null) {
            Alert alertWon = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alertWon.setTitle("The Game has ended!");
            alertWon.setHeaderText("Thanks for playing.");

            StringBuilder builder = new StringBuilder("The ");
            builder
                .append(board.getPlayerWon() == Player.WHITE ? "white" : "black")
                .append(" player has won! Congratulations.");

            alertWon.setContentText(builder.toString());

            alertWon.show();
        }
    }
}
