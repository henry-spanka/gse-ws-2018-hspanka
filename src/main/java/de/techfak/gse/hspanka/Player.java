package de.techfak.gse.hspanka;

/**
 * The player, either Black or White.
 */
public enum Player {
    BLACK, WHITE;

    /**
     * Parses a character (either 'w' or 'b') and returns the Player enum.
     * @param c The character to be parsed.
     * @return The Player object.
     * @throws IllegalArgumentException Thrown if the character is invalid.
     */
    public static Player playerFromChar(final char c) throws IllegalArgumentException {
        switch (c) {
            case 'w':
                return WHITE;
            case 'b':
                return BLACK;
            default:
                throw new IllegalArgumentException("The character supplied is now recognised.");
        }
    }

    /**
     * Converts the player object into a char in FEN notation, representing the player ('w' or 'b').
     * @return The player as a character.
     * @throws IllegalArgumentException Thrown if no character is known for the player.
     */
    public char toChar() throws IllegalArgumentException {
        switch (this) {
            case WHITE:
                return 'w';
            case BLACK:
                return 'b';
            default:
                throw new IllegalArgumentException("The subclass can not be converted to a char.");
        }
    }
}
