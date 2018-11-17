package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Move {
    private int cFrom;
    private int rFrom;
    private int cTo;
    private int rTo;

    public Move(final int cFrom, final int rFrom, final int cTo, final int rTo) {
        this.cFrom = cFrom;
        this.rFrom = rFrom;
        this.cTo = cTo;
        this.rTo = rTo;
    }

    public static List<Move> fromString(String m) throws InvalidMoveException {
        List<Move> moves = new ArrayList<>();

        int i = 0;
        while (i < m.length()) {
            if (i + 6 > m.length() || m.charAt(i + 2) != '-' || m.charAt(i + 5) != ';') {
                throw new InvalidMoveException("The format could not be recognised.");
            }

            char cFrom = m.charAt(i);
            char rFrom = m.charAt(i + 1);
            char cTo = m.charAt(i + 3);
            char rTo = m.charAt(i + 4);

            if (cFrom < 'a' || cFrom > 'h' || cTo < 'a' || cTo > 'h') {
                throw new InvalidMoveException("The column is out of bounds.");
            }

            if (rFrom < '1' || rFrom > '8' || rTo < '1' || rTo > '8') {
                throw new InvalidMoveException("The row is out of bounds.");
            }

            moves.add(new Move(cFrom, rFrom, cTo, rTo));

            i += 6;
        }

        return moves;
    }
}
