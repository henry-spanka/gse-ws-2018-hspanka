package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import de.techfak.gse.hspanka.exceptions.CannotMoveToOwnedPieceException;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {
    private static Board board;

    @BeforeAll
    public static void setUp() throws ApplicationErrorException {
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/8/8/8/8/4b3/3BK3/4r3 w");
    }

    @Test
    @VisibleForTesting
    void canMoveToNearbyFields() {
        // Move Right
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 5, 6));
        });

        // Move Forward
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 4, 5));
        });

        // Move Backward
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 4, 7));
        });

        // Move Diagonal
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 5, 7));
            board.setMove(new Move(4, 6, 3, 5));
            board.setMove(new Move(4, 6, 3, 7));
            board.setMove(new Move(4, 6, 5, 5));
        });
    }

    @Test
    @VisibleForTesting
    void canNotMoveToDistantFields() {
        // Cannot move two fields to the right
        assertThrows(ApplicationMoveException.class, () -> {
            board.setMove(new Move(4, 6, 6, 6));
        });

        // Cannot move two fields to the left
        assertThrows(ApplicationMoveException.class, () -> {
            board.setMove(new Move(4, 6, 2, 6));
        });

        // Cannot move two fields forward
        assertThrows(ApplicationMoveException.class, () -> {
            board.setMove(new Move(4, 6, 4, 4));
        });

        // Cannot move two fields diagonal (upper left)
        assertThrows(ApplicationMoveException.class, () -> {
            board.setMove(new Move(4, 6, 2, 4));
        });
    }

    @Test
    @VisibleForTesting
    void canNotMoveToAllyField() {
        // Check that the King cannot move to an allied field.
        assertThrows(CannotMoveToOwnedPieceException.class, () -> {
            board.setMove(new Move(4, 6, 3, 6));
        });
    }

    @Test
    @VisibleForTesting
    void canMoveToEnemyField() {
        // Bishop forward
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 4, 5));
        });

        // Rook backward
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 4, 7));
        });
    }
}
