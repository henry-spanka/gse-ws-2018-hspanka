package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.exceptions.InvalidBoardConfiguration;
import de.techfak.gse.hspanka.Player;

public abstract class Piece {
    private Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public static Piece fromChar(char c) throws InvalidBoardConfiguration {
        switch (c) {
            case 'k':
                return new King(Player.BLACK);
            case 'q':
                return new Queen(Player.BLACK);
            case 'r':
                return new Rook(Player.BLACK);
            case 'n':
                return new Knight(Player.BLACK);
            case 'b':
                return new Bishop(Player.BLACK);
            case 'p':
                return new Pawn(Player.BLACK);

            case 'K':
                return new King(Player.WHITE);
            case 'Q':
                return new Queen(Player.WHITE);
            case 'R':
                return new Rook(Player.WHITE);
            case 'N':
                return new Knight(Player.WHITE);
            case 'B':
                return new Bishop(Player.WHITE);
            case 'P':
                return new Pawn(Player.WHITE);

            default:
                throw new InvalidBoardConfiguration("The character " + c + " is not recognised");

        }
    }

    public char playerToChar(char c) {
        if (player == Player.BLACK) {
            return Character.toLowerCase(c);
        } else {
            return Character.toUpperCase(c);
        }
    }

    public abstract char toChar();

    public Player getPlayer() {
        return player;
    }
}
