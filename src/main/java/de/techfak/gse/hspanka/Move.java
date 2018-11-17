package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import de.techfak.gse.hspanka.exceptions.MoveToItselfException;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a move on the chess board that should be made.
 */
public class Move {
    /**
     * The column of the Piece which should be moved.
     */
    private int cFrom;

    /**
     * the row of the Piece which should be moved.
     */
    private int rFrom;

    /**
     * The column of the target piece/field.
     */
    private int cTo;

    /**
     * The row of the target piece/field.
     */
    private int rTo;

    /**
     * The constructor of the Move.
     * @param cFrom The column of the Piece which should be moved.
     * @param rFrom the row of the Piece which should be moved.
     * @param cTo The column of the target piece/field.
     * @param rTo The row of the target piece/field.
     */
    public Move(final int cFrom, final int rFrom, final int cTo, final int rTo) {
        this.cFrom = cFrom;
        this.rFrom = rFrom;
        this.cTo = cTo;
        this.rTo = rTo;
    }

    /**
     * Converts a String in FEN notation of moves to a List of Move objects.
     * @param m The string in FEN notatino.
     * @return The ArrayList of Move objects.
     * @throws InvalidMoveException Thrown if the string can not be parsed.
     */
    public static List<Move> fromString(String m) throws InvalidMoveException {
        List<Move> moves = new ArrayList<>();

        int i = 0;
        // Parse each character
        while (i < m.length()) {
            // Lookahead 6 characters to make sure the move has the correct length and syntax.
            if (i + 6 > m.length() || m.charAt(i + 2) != '-' || m.charAt(i + 5) != ';') {
                throw new InvalidMoveException("The format could not be recognised.");
            }

            // Convert the chars a to h to integers from 0 to 7.
            int cFrom = m.charAt(i) - 'a';
            int rFrom = m.charAt(i + 1) - '0' - 1;
            int cTo = m.charAt(i + 3) - 'a';
            int rTo = m.charAt(i + 4) - '0' - 1;

            // Check the bounds (0-7) of the move.
            if (cFrom < 0 || cTo < 0 || rFrom < 0 || rTo < 0 || cFrom > 7 || cTo > 7 || rFrom > 7 || rTo > 7) {
                throw new InvalidMoveException("Move out of bounds");
            }

            // Add the move to the Array list.
            moves.add(new Move(cFrom, rFrom, cTo, rTo));

            // Skip the next 6 characters as we have already parsed them.
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

    /**
     * Validate that a move is unique and that source does not match destination.
     * @throws MoveToItselfException Thrown if source and destination matches.
     */
    public void validateUniqueness() throws MoveToItselfException {
        if (cFrom == cTo && rFrom == rTo) {
            throw new MoveToItselfException("A piece can not be moved on itself.");
        }
    }
}
