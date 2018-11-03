package de.techfak.gse.hspanka;

public class ChessGame {
    public static void main(final String... args) {
        BoardController boardController = new BoardController();

        if (args.length > 0) {
            if (args[0].isEmpty()) {
                System.exit(0);
            }
        }

        boardController.showCurrentBoard();
    }

}
