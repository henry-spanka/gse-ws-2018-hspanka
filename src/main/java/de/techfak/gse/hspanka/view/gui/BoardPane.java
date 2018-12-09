package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.controller.gui.BoardController;
import de.techfak.gse.hspanka.piece.Piece;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

/**
 * Displays the board on the stage.
 */
public class BoardPane extends GridPane {
    /**
     * The BoardController so we can setup the event handler correctly.
     */
    private BoardController controller;

    public BoardPane() {
        super();
    }

    /**
     * Returns the StackPane at the specified position.
     * @param col The column.
     * @param row The row.
     * @return The StackPane or null if not found.
     */
    private StackPane getStackPane(int col, int row) {
        for (Node node : super.getChildren()) {
            Integer node_col = BoardPane.getColumnIndex(node);
            Integer node_row = BoardPane.getRowIndex(node);

            if (node_col != null && node_row != null && node_col == col && node_row == row) {
                return (StackPane) node;
            }
        }

        return null;
    }

    /**
     * Registers an event handler for the given row and column.
     * @param col The column where the event was fired.
     * @param row The row where the event was fired.
     * @param pane The pane on which the event handler should be registered.
     */
    private void registerEventHandler(int col, int row, StackPane pane) {
        EventHandler handler = event -> {
            controller.fieldClicked(col, row);
            event.consume();
        };

        pane.setOnMouseClicked(handler);
    }

    /**
     * Set's the color of a field to display the alternating grid colors.
     * @param col The column where the field is located.
     * @param row The row where the field is located.
     * @param pane The pane on which the color should be applied.
     */
    private void colorizeField(int col, int row, StackPane pane) {
        if ((row + col) % 2 == 0) {
            pane.setStyle("-fx-background-color: #e8ebef");
        } else {
            pane.setStyle("-fx-background-color: #7d8796");
        }
    }

    /**
     * Initializes the grid
     * @param controller The board controller on which the event handler's should be registered.
     */
    public void initialize(BoardController controller) {
        this.controller = controller;

        // Set the grid size.
        for (int i = 0; i < Board.FIELD_SIZE; i++) {
            getColumnConstraints().add(
                new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    HPos.CENTER, true
                )
            );
            getRowConstraints().add(
                new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true
                )
            );
        }

        // Create a square in each cell and color it.
        // Also registers the event handler.
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                StackPane square = new StackPane();
                colorizeField(col, row, square);

                registerEventHandler(col, row, square);

                add(square, col, row);
            }
        }
    }

    /**
     * Redraw's the grid.
     * @param pieces The pieces that should be drawed on the grid.
     * @param move The current player.
     */
    public void redraw(Piece[][] pieces, Move move) {
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            // Check each piece in a row from the left to the right.
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                Piece piece = pieces[row][col];

                StackPane square = getStackPane(col, row);

                // If we have a field without a piece, remove the image (if any) and set color.
                if (piece == null) {
                    if (square.getChildren() != null && !square.getChildren().isEmpty()) {
                        square.getChildren().remove(0, 1);
                        colorizeField(col, row, square);
                    }
                } else {
                    // Check if the field is involved in a move and color it appropriately.
                    if (move != null && move.isInvolved(col, row)) {
                        square.setStyle("-fx-background-color: #1e4156");
                    } else {
                        colorizeField(col, row, square);
                    }

                    StackPane piece_pane;
                    // Add the piece image onto the cell.
                    if (square.getChildren() == null || square.getChildren().isEmpty()) {
                        piece_pane = new StackPane();
                        square.getChildren().add(piece_pane);
                    } else {
                        piece_pane = (StackPane) square.getChildren().get(0);
                    }
                    PieceImage pieceImg = new PieceImage(piece);
                    piece_pane.setBackground(pieceImg.asBackground());
                }
            }
        }
    }
}
