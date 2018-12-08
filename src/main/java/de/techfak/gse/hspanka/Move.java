package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import de.techfak.gse.hspanka.exceptions.MoveToItselfException;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a move on the chess board that should be made.
 */
public final class Move {
    /**
     * The start of the target move position in FEN notation.
     */
    private static final int MOVE_TARGET_START = 3;

    /**
     * The length of a move in FEN notation.
     */
    private static final int MOVE_LENGTH = 6;

    /**
     * The column of the Piece which should be moved.
     */
    private final int cFrom;

    /**
     * the row of the Piece which should be moved.
     */
    private final int rFrom;

    /**
     * The column of the target piece/field.
     */
    private final int cTo;

    /**
     * The row of the target piece/field.
     */
    private final int rTo;

    /**
     * The constructor of the Move.
     *
     * @param cFrom The column of the Piece which should be moved.
     * @param rFrom the row of the Piece which should be moved.
     * @param cTo   The column of the target piece/field.
     * @param rTo   The row of the target piece/field.
     */
    private Move(final int cFrom, final int rFrom, final int cTo, final int rTo) {
        this.cFrom = cFrom;
        this.rFrom = rFrom;
        this.cTo = cTo;
        this.rTo = rTo;
    }

    /**
     * Converts a String in FEN notation of moves to a List of Move objects.
     *
     * @param moveString The string in FEN notation.
     * @return The ArrayList of Move objects.
     * @throws InvalidMoveException Thrown if the string can not be parsed.
     */
    public static List<Move> fromString(final String moveString) throws InvalidMoveException {
        final List<Move> moves = new ArrayList<>();

        int pos = 0;
        // Parse each character
        while (pos < moveString.length()) {
            // Lookahead MOVE_LENGTH characters to make sure the move has the correct length and syntax.
            if (pos + MOVE_LENGTH > moveString.length() || moveString.charAt(pos + 2) != '-' ||
                moveString.charAt(pos + MOVE_LENGTH - 1) != ';') {
                throw new InvalidMoveException("The format could not be recognised.");
            }

            // Convert the chars a to h to integers from 0 to 7.
            final int cFrom = moveString.charAt(pos) - 'a';
            final int rFrom = moveString.charAt(pos + 1) - '0' - 1;
            final int cTo = moveString.charAt(pos + MOVE_TARGET_START) - 'a';
            final int rTo = moveString.charAt(pos + MOVE_TARGET_START + 1) - '0' - 1;

            // Check the bounds (0-7) of the move.
            if (cFrom < 0 || cTo < 0 || rFrom < 0 || rTo < 0 ||
                cFrom > MOVE_LENGTH + 1 || cTo > MOVE_LENGTH + 1 || rFrom > MOVE_LENGTH + 1 || rTo > MOVE_LENGTH + 1) {
                throw new InvalidMoveException("Move out of bounds");
            }

            // Add the move to the Array list.
            // Invert the positions as the board is indexed from top-left to right-bottom.
            moves.add(new Move(
                Math.abs(cFrom - Board.FIELD_SIZE + 1),
                Math.abs(rFrom - Board.FIELD_SIZE + 1),
                Math.abs(cTo - Board.FIELD_SIZE + 1),
                Math.abs(rTo - Board.FIELD_SIZE + 1)
            ));

            // Skip the next MOVE_LENGTH characters as we have already parsed them.
            pos += MOVE_LENGTH;
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
     *
     * @throws MoveToItselfException Thrown if source and destination matches.
     */
    public void validateUniqueness() throws MoveToItselfException {
        if (cFrom == cTo && rFrom == rTo) {
            throw new MoveToItselfException("A piece can not be moved on itself.");
        }
    }
}
