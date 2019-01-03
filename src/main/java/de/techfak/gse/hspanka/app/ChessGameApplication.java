package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;

/**
 * A interface for interacting with the application.
 */
public interface ChessGameApplication {
    /**
     * Starts the application.
     *
     * @param args The command line parameters passed on execution.
     * @throws ApplicationErrorException Thrown if an error occured during execution.
     */
    void run(String... args) throws ApplicationErrorException;

}
