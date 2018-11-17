package de.techfak.gse.hspanka.Exceptions;

/**
 * Exception that should be thrown if the move is invalid.
 */
public class MoveToItselfException extends ApplicationMoveException {
    public MoveToItselfException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 104;
    }
}
