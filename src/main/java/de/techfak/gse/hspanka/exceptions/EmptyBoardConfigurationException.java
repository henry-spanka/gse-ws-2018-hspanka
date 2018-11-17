package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the board configuration is empty.
 */
public class EmptyBoardConfigurationException extends ApplicationErrorException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 0;

    @SuppressWarnings("unused")
    public EmptyBoardConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmptyBoardConfigurationException(final String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
