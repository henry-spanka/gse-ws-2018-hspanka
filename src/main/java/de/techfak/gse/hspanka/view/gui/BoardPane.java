package de.techfak.gse.hspanka.view.gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

public class BoardPane extends GridPane {
    public BoardPane() {
        super();
    }

    public void initWithSize(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                StackPane square = new StackPane();
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #e8ebef");
                } else {
                    square.setStyle("-fx-background-color: #7d8796");
                }
                add(square, col, row);
            }
        }

        for (int i = 0; i < size; i++) {
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
    }
}
