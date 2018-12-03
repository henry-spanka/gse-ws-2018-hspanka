package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.app.ChessGameApplication;
import de.techfak.gse.hspanka.app.ChessGameApplicationFactory;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;

/**
 * The main class that is invoked on startup.
 * Responsible for initialising the controller.
 */
class ChessGame {
    /**
     * Bootstrap the Application.
     * This function initialises the controller and handles input from the command line.
     *
     * @param args Command line arguments supplied on execution
     */
    @SuppressWarnings({"PMD.DoNotCallSystemExit", "PMD.AvoidInstanceofChecksInCatchClause"})
    public static void main(final String... args) {
        ChessGameApplicationFactory appFactory = new ChessGameApplicationFactory();

        try {
            ChessGameApplication app;

            app = appFactory.makeConsoleApplication();

            app.run();
        } catch (ApplicationErrorException e) {
            // Terminate the program with the exit code specified in the exception class.
            System.exit(e.getErrorCode());
        }
    }

}
