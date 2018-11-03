package de.techfak.gse.hspanka.Exceptions;

public abstract class ApplicationErrorException extends Exception {
    public ApplicationErrorException() {
        super();
    }

    public ApplicationErrorException(String message) {
        super(message);
    }

    public abstract int getErrorCode();
}
