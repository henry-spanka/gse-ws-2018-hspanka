package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import de.techfak.gse.hspanka.exceptions.MoveToItselfException;

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

            int cFrom = m.charAt(i) - 'a';
            int rFrom = m.charAt(i + 1) - '0' - 1;
            int cTo = m.charAt(i + 3) - 'a';
            int rTo = m.charAt(i + 4) - '0' - 1;

            if (cFrom < 0 || cTo < 0 || rFrom < 0 || rTo < 0 || cFrom > 7 || cTo > 7 || rFrom > 7 || rTo > 7) {
                throw new InvalidMoveException("Move out of bounds");
            }

            moves.add(new Move(cFrom, rFrom, cTo, rTo));

            i += 6;
        }

        return moves;
    }

    public int getcFrom() {
        return cFrom;
    }

    public int getrFrom() {
        return rFrom;
    }

    public int getcTo() {
        return cTo;
    }

    public int getrTo() {
        return rTo;
    }

    public void validateUniqueness() throws MoveToItselfException {
        if (cFrom == cTo && rFrom == rTo) {
            throw new MoveToItselfException("A piece can not be moved on itself.");
        }
    }
}
