package de.techfak.gse.hspanka;

public enum Player {
    BLACK, WHITE;

    public static Player playerFromChar(char c) throws IllegalArgumentException {
        switch (c) {
            case 'w':
                return WHITE;
            case 'b':
                return BLACK;
        }

        throw new IllegalArgumentException("The character supplied is now recognised.");
    }

    public char toChar() {
        switch (this) {
            case WHITE:
                return 'w';
            case BLACK:
                return 'b';
        }

        throw new IllegalArgumentException("The subclass can not be converted to a char.");
    }
}
