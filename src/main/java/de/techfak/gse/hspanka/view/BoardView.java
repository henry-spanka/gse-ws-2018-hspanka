package de.techfak.gse.hspanka.view;

import de.techfak.gse.hspanka.piece.Piece;
import de.techfak.gse.hspanka.Player;

import java.lang.System;

public class BoardView {
    public void showCurrentConfiguration(Piece[][] configuration, Player player) {
        int emptyPieces = 0;
        StringBuilder output = new StringBuilder();

        for (int row = 7; row >= 0; row--) {
            for (Piece piece: configuration[row]) {
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

            if (row > 0) {
                output.append('/');
            }
        }

        output.append(' ');
        output.append(player.toChar());

        System.out.println(output);
    }
}
