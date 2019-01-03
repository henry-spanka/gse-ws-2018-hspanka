package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Constraint;
import de.techfak.gse.hspanka.ConstraintFieldGenerator;
import de.techfak.gse.hspanka.Player;

/**
 * A specific Piece that is used to play Chess.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chess#Setup">Chess Setup</a>
 */
public class Pawn extends Piece {
    /**
     * The constraint field generator for the Pawn.
     */
    private static final ConstraintFieldGenerator CONSTRAINT_FIELD_GENERATOR =
        new ConstraintFieldGenerator()
            .addConstraint(Constraint.Direction.FORWARD, 1, 1, false, true, true)
            .addConstraint(Constraint.Direction.DIAGONAL_FORWARD, 1, 1, false, true, false, true);
    public Pawn(final Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('p');
    }

    @Override
    public ConstraintFieldGenerator getConstraintFieldGenerator() {
        return CONSTRAINT_FIELD_GENERATOR;
    }
}
