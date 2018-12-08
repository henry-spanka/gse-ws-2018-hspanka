package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.piece.Piece;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

public class BoardPane extends GridPane {
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

    public void initialize() {
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
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #e8ebef");
                } else {
                    square.setStyle("-fx-background-color: #7d8796");
                }

                add(square, col, row);
            }
        }
    }

    public void redraw(Piece[][] pieces) {
        for (int row = 0; row < Board.FIELD_SIZE; row++) {
            // Check each piece in a row from the left to the right.
            for (int col = 0; col < Board.FIELD_SIZE; col++) {
                Piece piece = pieces[row][col];

                if (piece == null) {
                    // TODO: Remove Background
                } else {
                    StackPane stackPane = getStackPane(col, row);

                    StackPane piece_pane;
                    if (stackPane.getChildren() == null || stackPane.getChildren().isEmpty()) {
                        piece_pane = new StackPane();
                        stackPane.getChildren().add(piece_pane);
                    } else {
                        piece_pane = (StackPane) stackPane.getChildren().get(0);
                    }
                    PieceImage pieceImg = new PieceImage(piece);
                    piece_pane.setBackground(pieceImg.asBackground());
                }
            }
        }
    }
}
