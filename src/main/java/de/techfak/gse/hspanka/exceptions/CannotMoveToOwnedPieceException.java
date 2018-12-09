package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if a piece is not owned by the current player.
 */
public class CannotMoveToOwnedPieceException extends ApplicationMoveException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = 6812931052596083882L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 103;

    public CannotMoveToOwnedPieceException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public CannotMoveToOwnedPieceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
