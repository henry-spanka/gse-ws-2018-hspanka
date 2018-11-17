package de.techfak.gse.hspanka.Exceptions;

/**
 * Exception that should be thrown if a piece is expected at the position of the board but not found
 */
public class BoardPositionEmptyException extends ApplicationMoveException {
    public BoardPositionEmptyException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 102;
    }
}
