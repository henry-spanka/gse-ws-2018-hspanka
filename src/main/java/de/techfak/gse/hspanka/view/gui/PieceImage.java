package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Displays the piece's image.
 */
class PieceImage extends Image {
    /**
     * The location of the images.
     */
    public static final String IMG_LOCATION = "images/";
    /**
     * The image size in relatively to the cell in percentage.
     */
    private static final double IMAGE_SIZE = 0.75;

    /**
     * Initializes the piece image.
     * @param piece The piece which should be shown.
     */
    public PieceImage(final Piece piece) {
        super(IMG_LOCATION + getImageName(piece));
    }

    /**
     * Convert's a piece to the corresponding image name.
     * @param piece The piece to be converted.
     * @return The image name.
     */
    private static String getImageName(final Piece piece) {
        switch (piece.toChar()) {
            case 'k':
                return "king_black.png";
            case 'q':
                return "queen_black.png";
            case 'r':
                return "rook_black.png";
            case 'n':
                return "knight_black.png";
            case 'b':
                return "bishop_black.png";
            case 'p':
                return "pawn_black.png";

            case 'K':
                return "king_white.png";
            case 'Q':
                return "queen_white.png";
            case 'R':
                return "rook_white.png";
            case 'N':
                return "knight_white.png";
            case 'B':
                return "bishop_white.png";
            case 'P':
                return "pawn_white.png";

            default:
                throw new IllegalArgumentException("The character " + piece.toChar() + " is not recognised");
        }
    }

    /**
     * Returns the image wrapped into a background.
     * @return The JavaFX background.
     */
    public Background asBackground() {
        return new Background(
            new BackgroundImage(this,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                    IMAGE_SIZE,
                    IMAGE_SIZE,
                    true,
                    true,
                    false,
                    false
                )
            )
        );
    }
}
