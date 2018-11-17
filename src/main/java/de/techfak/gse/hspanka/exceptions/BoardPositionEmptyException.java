package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if a piece is expected at the position of the board but not found
 */
public class BoardPositionEmptyException extends ApplicationMoveException {
    private static final int ERR_CODE = 102;

    public BoardPositionEmptyException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
