package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if a piece is expected at the position of the board but not found.
 */
public class BoardPositionEmptyException extends ApplicationMoveException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = -4153107866378269230L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 102;

    public BoardPositionEmptyException(final String message) {
        super(message);
    }

    public BoardPositionEmptyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    @SuppressWarnings("unused")
    public int getErrorCode() {
        return ERR_CODE;
    }
}
