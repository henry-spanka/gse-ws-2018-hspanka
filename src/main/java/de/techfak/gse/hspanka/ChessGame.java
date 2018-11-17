package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.ApplicationErrorException;

import java.util.Scanner;

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

            Scanner terminalInput = new Scanner(System.in);

            while (true) {
                boardController.showCurrentBoard();

                String nextLine = terminalInput.nextLine();

                for (Move move: Move.fromString(nextLine)) {
                    boardController.makeMove(move);
                }
            }
        } catch (ApplicationErrorException e) {
            System.exit(e.getErrorCode());
        }
    }

}
