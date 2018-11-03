package de.techfak.gse.hspanka.Exceptions;

/**
 * Exception that should be thrown if the board configuration is invalid
 */
public class InvalidBoardConfiguration extends ApplicationErrorException {
    public InvalidBoardConfiguration(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 100;
    }
}
