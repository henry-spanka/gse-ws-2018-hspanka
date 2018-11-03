package de.techfak.gse.hspanka.Exceptions;

public class InvalidBoardConfiguration extends ApplicationErrorException {
    public InvalidBoardConfiguration(String message) {
        super(message);
    }

    @Override
    public int getErrorCode() {
        return 100;
    }
}
