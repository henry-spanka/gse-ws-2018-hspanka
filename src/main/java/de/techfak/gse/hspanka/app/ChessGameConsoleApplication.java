package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.controller.console.BoardController;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.EmptyCommandException;

import java.util.Scanner;

public class ChessGameConsoleApplication implements ChessGameApplication {
    @Override
    /**
     * This function initialises the controller and handles input from the command line.
     *
     * @param args Command line arguments supplied on execution
     */
    public void run(String... args) throws ApplicationErrorException {
        final BoardController boardController = new BoardController();

        try {
            // Parse the comand line argument as a board configuration if specified.
            if (args.length > 0) {
                boardController.setBoardConfigurationFromString(args[0]);
            } else {
                boardController.setDefaultBoardConfiguration();
            }

            // Read moves from the command line.
            final Scanner terminalInput = new Scanner(System.in);

            //noinspection InfiniteLoopStatement
            while (true) {
                // Show the current board after we made a move.
                boardController.showCurrentBoard();

                // Read a move from the command line.
                final String nextLine = terminalInput.nextLine();

                if (nextLine.isEmpty()) {
                    throw new EmptyCommandException("An empty command was supplied");
                }

                // Parse the moves and execute them.
                for (final Move move : Move.fromString(nextLine)) {
                    boardController.makeMove(move);
                }
            }
        } catch (ApplicationErrorException e) {
            // Print the board before exiting. This means the exception was thrown during a move.
            if (e instanceof ApplicationMoveException) {
                boardController.showCurrentBoard();
            }

            throw e;
        }
    }
}
