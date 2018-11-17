package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the board configuration is invalid.
 */
public class InvalidBoardConfiguration extends ApplicationErrorException {
    /**
     * The exit code the application should exit with.
     */
    private static final int ERR_CODE = 100;

    public InvalidBoardConfiguration(final String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
