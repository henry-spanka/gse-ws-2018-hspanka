package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.CannotMoveToOwnedPieceException;
import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {
    private static Board board;

    @Test
    void can_move_forward() {
        // Move Forward
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6, 4, 5));
        });
    }

    @Test
    void can_not_make_illegal_moves() {
        // Move two forward
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove(new Move(4, 6, 4, 4));
        });
    }

    @Test
    void can_not_move_forward_to_occupied_field() {
        // Move forward to the enemy rook
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove((new Move(4, 2, 4, 1)));
        });
    }

    @BeforeAll
    public static void setUp() throws ApplicationErrorException {
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/4r3/4P3/1K6/4r3/8/4P3/8 w");
    }
}
