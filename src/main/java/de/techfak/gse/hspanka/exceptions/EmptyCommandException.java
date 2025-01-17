package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the board configuration is empty.
 */
public class EmptyCommandException extends ApplicationErrorException {
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final long serialVersionUID = -3732519706559813015L;

    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 0;

    public EmptyCommandException(final String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public EmptyCommandException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
