package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.Exceptions.ApplicationErrorException;

public class ChessGame {
    public static void main(final String... args) {
        try {
            BoardController boardController = new BoardController();

            if (args.length > 0) {
                boardController.setBoardConfigurationFromString(args[0]);
            }

            boardController.showCurrentBoard();
        } catch (ApplicationErrorException e) {
            System.out.println(e.getErrorCode());
            System.exit(e.getErrorCode());
        }
    }

}
