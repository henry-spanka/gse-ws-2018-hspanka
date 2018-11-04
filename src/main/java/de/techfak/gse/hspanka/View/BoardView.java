package de.techfak.gse.hspanka.View;

import de.techfak.gse.hspanka.Piece.Piece;
import de.techfak.gse.hspanka.Player;

import java.lang.System;

public class BoardView {
    public void showCurrentConfiguration(Piece[][] configuration, Player player) {
        int emptyPieces = 0;
        StringBuilder output = new StringBuilder();

        for (int row = 0; row < configuration.length; row++) {
            for (Piece piece : configuration[row]) {
                if (piece == null) {
                    emptyPieces++;
                } else {
                    if (emptyPieces > 0) {
                        output.append(emptyPieces);
                        emptyPieces = 0;
                    }

                    output.append(piece.toChar());
                }
            }

            if (emptyPieces > 0) {
                output.append(emptyPieces);
                emptyPieces = 0;
            }

            if (row < configuration.length-1) {
                output.append('/');
            }
        }

        output.append(' ');
        output.append(player.toChar());

        System.out.println(output);
    }
}
