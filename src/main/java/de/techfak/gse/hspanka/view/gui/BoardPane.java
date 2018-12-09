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

public class BoardPane extends GridPane {
    /**
     * The BoardController so we can setup the event handler correctly.
     */
    private BoardController controller;

    public BoardPane() {
        super();
    }

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

    private void registerEventHandler(int col, int row, StackPane pane) {
        EventHandler handler = event -> {
            controller.fieldClicked(col, row);
            event.consume();
        };

        pane.setOnMouseClicked(handler);
    }

    private void colorizeField(int col, int row, StackPane pane) {
        if ((row + col) % 2 == 0) {
            pane.setStyle("-fx-background-color: #e8ebef");
        } else {
            pane.setStyle("-fx-background-color: #7d8796");
        }
    }

    public void initialize(BoardController controller) {
        this.controller = controller;

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

        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                StackPane square = new StackPane();
                colorizeField(col, row, square);

                registerEventHandler(col, row, square);

                add(square, col, row);
            }
        }
    }

    public void redraw(Piece[][] pieces, Move move) {
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            // Check each piece in a row from the left to the right.
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                Piece piece = pieces[row][col];

                StackPane square = getStackPane(col, row);

                if (piece == null) {
                    if (square.getChildren() != null && !square.getChildren().isEmpty()) {
                        square.getChildren().remove(0, 1);
                        colorizeField(col, row, square);
                    }
                } else {
                    if (move != null && move.isInvolved(col, row)) {
                        square.setStyle("-fx-background-color: #1e4156");
                    } else {
                        colorizeField(col, row, square);
                    }

                    StackPane piece_pane;
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
