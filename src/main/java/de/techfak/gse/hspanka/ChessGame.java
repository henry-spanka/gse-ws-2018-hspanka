package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.ApplicationErrorException;

/**
 * The main class that is invoked on startup. Responsible for initialising the controller.
 */
public class ChessGame {
    /**
     * Bootstrap the Application
     *
     * @param args Command line arguments supplied on execution
     */
    public static void main(final String... args) {
        try {
            BoardController boardController = new BoardController();

            if (args.length > 0) {
                boardController.setBoardConfigurationFromString(args[0]);
            } else {
                boardController.setDefaultBoardConfiguration();
            }

            boardController.showCurrentBoard();
        } catch (ApplicationErrorException e) {
            System.exit(e.getErrorCode());
        }
    }

}
