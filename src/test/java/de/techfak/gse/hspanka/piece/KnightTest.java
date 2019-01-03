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

class KnightTest {
    private static Board board;

    @Test
    void can_make_valid_moves() {
        // Move two right, followed by one field up
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 5, 3));
        });

        // Move two left, followed by one field down
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 1, 5));
        });

        // Move two down, followed by one field right
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 4, 6));
        });

        // Move two down, followed by one field left
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 2, 6));
        });
    }

    @Test
    void can_not_make_illegal_moves() {
        // Move one up
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove(new Move(3, 4, 3, 3));
        });

        // Move two up, followed by two fields right
        assertThrows(InvalidMoveException.class, () -> {
            board.setMove(new Move(3, 4, 5, 2));
        });
    }

    @Test
    void can_move_to_enemy_field() {
        // Move two up and one left to the enemy King
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 2, 2));
        });

        // Move two right and one down to the enemy Knight
        assertDoesNotThrow(() -> {
            board.setMove(new Move(3, 4, 5, 5));
        });
    }

    @BeforeAll
    public static void setUp() throws ApplicationErrorException {
        board = new Board();
        final FenParser fen = new FenParser(board);
        fen.parse("8/8/2k5/8/3N4/5n2/8/8 w");
    }
}
