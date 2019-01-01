package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.ApplicationMoveException;
import static org.junit.jupiter.api.Assertions.*;

import de.techfak.gse.hspanka.exceptions.CannotMoveToOwnedPieceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class KingTest {
    private static Board board;

    @Test
    void can_move_to_nearby_fields() {
        // Move Right
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 5, 6), true);
        });

        // Move Forward
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 4, 5), true);
        });

        // Move Backward
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 4, 7), true);
        });

        // Move Diagonal
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 5, 7), true);
            board.validateMove(new Move(4, 6, 3, 5), true);
            board.validateMove(new Move(4, 6, 3, 7), true);
            board.validateMove(new Move(4, 6, 5, 5), true);
        });
    }

    @Test
    void can_not_move_to_distant_fields() {
        // Cannot move two fields to the right
        assertThrows(ApplicationMoveException.class, () -> {
            board.validateMove(new Move(4, 6, 6, 6), true);
        });

        // Cannot move two fields to the left
        assertThrows(ApplicationMoveException.class, () -> {
            board.validateMove(new Move(4, 6, 2, 6), true);
        });

        // Cannot move two fields forward
        assertThrows(ApplicationMoveException.class, () -> {
            board.validateMove(new Move(4, 6, 4, 4), true);
        });

        // Cannot move two fields diagonal (upper left)
        assertThrows(ApplicationMoveException.class, () -> {
            board.validateMove(new Move(4, 6, 2, 4), true);
        });
    }

    @Test
    void can_not_move_to_ally_field() {
        // Check that the King cannot move to an allied field.
        assertThrows(CannotMoveToOwnedPieceException.class, () -> {
            board.validateMove(new Move(4, 6, 3, 6), true);
        });
    }

    @Test
    void can_move_to_enemy_field() {
        // Bishop forward
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 4, 5), true);
        });

        // Rook backward
        assertDoesNotThrow(() -> {
            board.validateMove(new Move(4, 6, 4, 7), true);
        });
    }

    @BeforeAll
    public static void setUp() throws ApplicationErrorException{
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/8/8/8/8/4b3/3BK3/4r3 w");
    }
}
