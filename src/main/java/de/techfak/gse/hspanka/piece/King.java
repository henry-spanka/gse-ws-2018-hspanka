package de.techfak.gse.hspanka.piece;

import de.techfak.gse.hspanka.Constraint;
import de.techfak.gse.hspanka.ConstraintFieldGenerator;
import de.techfak.gse.hspanka.Player;

/**
 * A specific Piece that is used to play Chess.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Chess#Setup">Chess Setup</a>
 */
public class King extends Piece {
    /**
     * The constraint field generator for the King.
     */
    private static final ConstraintFieldGenerator CONSTRAINT_FIELD_GENERATOR =
        new ConstraintFieldGenerator()
        .addConstraint(Constraint.Direction.HORIZONTAL, 1, 1, false)
        .addConstraint(Constraint.Direction.VERTICAL, 1, 1, false)
        .addConstraint(Constraint.Direction.DIAGONAL, 1, 1, false);

    public King(final Player player) {
        super(player);
    }

    @Override
    public char toChar() {
        return playerToChar('k');
    }

    @Override
    public ConstraintFieldGenerator getConstraintFieldGenerator() {
        return CONSTRAINT_FIELD_GENERATOR;
    }
}
