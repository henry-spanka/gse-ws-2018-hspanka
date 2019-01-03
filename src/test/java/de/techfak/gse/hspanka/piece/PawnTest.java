package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Board;
import de.techfak.gse.hspanka.FenParser;
import de.techfak.gse.hspanka.Move;
import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import de.techfak.gse.hspanka.exceptions.CannotMoveToOwnedPieceException;
import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
            board.setMove(new Move(4, 2, 4, 1));
        });
    }

    @Test
    void can_not_jump_to_unoccupied_field() {
        // Move forward to the enemy rook
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove(new Move(4, 2, 4, 0));
        });
    }


    @Test
    void can_move_forward_diagonal_to_occupied_field() {
        // Move forward diagonal to the enemy rook
        assertDoesNotThrow(() -> {
            board.setMove(new Move(4, 6,3, 5));
        });
    }

    @Test
    void can_not_move_backward_diagonal_to_occupied_field() {
        // Move backward diagonal to the enemy rook
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove(new Move(4, 6,3, 7));
        });
    }

    @Test
    void can_move_two_forward_if_untouched() {
        // Move two forward to empty field.
        assertDoesNotThrow(() -> {
            board.setMove(new Move(0, 6,0, 4));
        });
    }

    @Test
    void can_not_move_two_forward_if_touched() {
        // Move two forward if touched.
        assertThrows(InvalidMoveException.class, () -> {
            board.getPiece(6, 0).touch();
            board.setMove(new Move(0, 6,0, 4));
        });
    }

    @BeforeEach
    public void setUp() throws ApplicationErrorException {
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/4r3/4P3/1K6/4r3/3r4/P3P3/3r4 w");
    }
}
