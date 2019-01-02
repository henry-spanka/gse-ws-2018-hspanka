package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {
    private static Board board;

    @Test
    void can_move_to_nearby_fields() {
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
            board.setMove(new Move(4, 6, 2, 4));
            board.setMove(new Move(4, 6, 3, 7));
            board.setMove(new Move(4, 6, 7, 3));
        });
    }

    @Test
    void can_not_jump_over_occupied_fields() {
        // Move three forward to the enemy rook
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove((new Move(4, 6, 4, 3)));
        });
    }

    @BeforeAll
    public static void setUp() throws ApplicationErrorException {
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/8/8/8/4r3/8/4Q3/8 w");
    }
}
