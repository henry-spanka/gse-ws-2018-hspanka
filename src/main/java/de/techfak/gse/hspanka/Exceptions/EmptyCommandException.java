package de.techfak.gse.hspanka.Exceptions;

/**
 * Exception that should be thrown if the board configuration is empty
 */
public class EmptyCommandException extends ApplicationErrorException {
    public EmptyCommandException(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 0;
    }
}
