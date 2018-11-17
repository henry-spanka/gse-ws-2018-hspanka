package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.Exceptions.BoardPositionEmptyException;
import de.techfak.gse.hspanka.Exceptions.EmptyCommandException;
import de.techfak.gse.hspanka.Exceptions.InvalidMoveException;

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
        BoardController boardController = new BoardController();

        try {

            if (args.length > 0) {
                boardController.setBoardConfigurationFromString(args[0]);
            } else {
                boardController.setDefaultBoardConfiguration();
            }

            Scanner terminalInput = new Scanner(System.in);

            while (true) {
                boardController.showCurrentBoard();

                String nextLine = terminalInput.nextLine();

                if (nextLine.isEmpty()) {
                    throw new EmptyCommandException("An empty command was supplied");
                }

                for (Move move: Move.fromString(nextLine)) {
                    boardController.makeMove(move);
                }
            }
        } catch (ApplicationErrorException e) {
            if (e instanceof InvalidMoveException || e instanceof BoardPositionEmptyException) {
                boardController.showCurrentBoard();
            }

            System.exit(e.getErrorCode());
        }
    }

}
