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
     *
     * @param args Command line arguments supplied on execution
     */
    @SuppressWarnings("PMD.DoNotCallSystemExit")
    public static void main(final String... args) {
        ChessGameApplicationFactory appFactory = new ChessGameApplicationFactory();

        try {
            ChessGameApplication app;

            // Parse the command line argument if '--gui' is set
            if (args.length > 0 && args[0].equals("--gui")) {
                app = appFactory.makeGuiApplication();
            } else {
                app = appFactory.makeConsoleApplication();
            }

            app.run();
        } catch (ApplicationErrorException e) {
            // Terminate the program with the exit code specified in the exception class.
            System.exit(e.getErrorCode());
        }
    }

}
