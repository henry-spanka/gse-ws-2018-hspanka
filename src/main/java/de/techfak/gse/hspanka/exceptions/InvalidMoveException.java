package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class InvalidMoveException extends ApplicationMoveException {
    public InvalidMoveException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 101;
    }
}
