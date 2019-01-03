package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Constraint;
import de.techfak.gse.hspanka.ConstraintFieldGenerator;
import de.techfak.gse.hspanka.Player;
import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;

/**
 * A abstract Piece is used to play Chess.
 * This class should be extended by all piece types.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chess#Setup">Chess Setup</a>
 */
public abstract class Piece {
    /**
     * The player who's piece this is.
     */
    private final Player player;

    /**
     * Whether the piece has been touched yet.
     */
    private boolean touched = false;

    /**
     * Create a piece.
     *
     * @param player The player who owns the piece.
     */
    Piece(final Player player) {
        this.player = player;
    }

    /**
     * Returns the Piece object from a character as specified in FEN notation.
     *
     * @param piece The char to be converted.
     *              Lowercase characters correspond to the black player and uppercase
     *              to the white player.
     * @return A piece subclass.
     * @throws InvalidBoardConfiguration Thrown if the character can not be recognised.
     */
    public static Piece fromChar(final char piece) throws InvalidBoardConfiguration {
        switch (piece) {
            case 'k':
                return new King(Player.BLACK);
            case 'q':
                return new Queen(Player.BLACK);
            case 'r':
                return new Rook(Player.BLACK);
            case 'n':
                return new Knight(Player.BLACK);
            case 'b':
                return new Bishop(Player.BLACK);
            case 'p':
                return new Pawn(Player.BLACK);

            case 'K':
                return new King(Player.WHITE);
            case 'Q':
                return new Queen(Player.WHITE);
            case 'R':
                return new Rook(Player.WHITE);
            case 'N':
                return new Knight(Player.WHITE);
            case 'B':
                return new Bishop(Player.WHITE);
            case 'P':
                return new Pawn(Player.WHITE);

            default:
                throw new InvalidBoardConfiguration("The character " + piece + " is not recognised");

        }
    }

    /**
     * Converts a character depending on player owning the Piece.
     * If the player is black then the character will be converted to lower case.
     * Otherwise (white player) to upper case.
     *
     * @param player The character to be converted.
     * @return The converted character (Piece in FEN notation).
     */
    @SuppressWarnings("WeakerAccess")
    public char playerToChar(final char player) {
        if (this.player == Player.BLACK) {
            return Character.toLowerCase(player);
        } else {
            return Character.toUpperCase(player);
        }
    }

    /**
     * Converts the piece to a character in FEN notation.
     *
     * @return The character represented by the piece in FEN notation.
     * @see <a href="{https://tdpe.techfak.uni-bielefeld.de/projects/gse-ws-2018/wiki/Schachnotation">Chess notation</a>
     */
    public abstract char toChar();

    /**
     * Get the current player.
     *
     * @return the current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Whether the piece has been touched yet.
     *
     * @return true if the piece has been touched.
     */
    public boolean hasBeenTouched() {
        return touched;
    }

    /**
     * Touch the piece.
     */
    public void touch() {
        touched = true;
    }

    /**
     * Get the constraint field generator for the piece.
     * @return The constraint field generator.
     */
    public abstract ConstraintFieldGenerator getConstraintFieldGenerator();
}
