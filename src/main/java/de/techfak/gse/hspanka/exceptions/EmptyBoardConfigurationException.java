package de.techfak.gse.hspanka.exceptions;

/**
 * Exception that should be thrown if the board configuration is empty
 */
public class EmptyBoardConfigurationException extends ApplicationErrorException {
    private static final int ERR_CODE = 0;

    public EmptyBoardConfigurationException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return ERR_CODE;
    }
}
