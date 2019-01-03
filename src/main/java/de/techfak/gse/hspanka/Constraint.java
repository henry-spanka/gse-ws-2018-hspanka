package de.techfak.gse.hspanka;

/**
 * Class defines a constraint that can be verified.
 */
public class Constraint {
    /**
     * The direction the constraint should be enforced.
     */
    private Direction direction;
    /**
     * The minimum step size.
     */
    private int min;
    /**
     * The maximum step size.
     */
    private int max;
    /**
     * Whether the fields in between must be empty.
     */
    private boolean empty;
    /**
     * Whether the target field must be empty or occupied.
     */
    private Target target;
    /**
     * A constraint that must be matched after the original.
     */
    private Constraint then;

    /**
     * Instaniates a new constraint.
     *
     * @param direction The direction to be enforced.
     * @param min       The minimium step size.
     * @param max       The maximum step size.
     */
    public Constraint(final Direction direction, final int min, final int max) {
        this(direction, min, max, true, Target.EMPTY_OR_OCCUPIPED);
    }

    /**
     * Instaniates a new constraint.
     *
     * @param direction The direction to be enforced.
     * @param min       The minimium step size.
     * @param max       The maximum step size.
     * @param empty     Whether the fields in between must be empty.
     */
    public Constraint(final Direction direction, final int min, final int max, final boolean empty) {
        this(direction, min, max, empty, Target.EMPTY_OR_OCCUPIPED);
    }

    /**
     * Instaniates a new constraint.
     *
     * @param direction The direction to be enforced.
     * @param min       The minimium step size.
     * @param max       The maximum step size.
     * @param empty     Whether the fields in between must be empty.
     * @param target    Whether the target field must be empty or occupied.
     */
    public Constraint(
        final Direction direction,
        final int min,
        final int max,
        final boolean empty,
        final Target target
    ) {
        this.direction = direction;
        this.min = min;

        if (max == -1) {
            this.max = Board.FIELD_SIZE - 1;
        } else {
            this.max = max;
        }

        this.empty = empty;
        this.target = target;
    }

    /**
     * Get the direction to be enforced.
     *
     * @return The direction,
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get minimum step size.
     *
     * @return The minimum step size.
     */
    public int getMin() {
        return min;
    }

    /**
     * Get the maximum step size.
     *
     * @return The maximum step size.
     */
    public int getMax() {
        return max;
    }

    /**
     * Whether the fields in between must be empty.
     *
     * @return True if they must be empty.
     */
    public boolean fieldsInBetweenMustBeEmpty() {
        return empty;
    }

    /**
     * Whether the target field must be empty.
     *
     * @return True if it must be empty.
     */
    public boolean targetFieldMustBeEmpty() {
        return target == Target.EMPTY;
    }

    /**
     * Whether the target field must be occupied.
     */
    public boolean targetFieldMustBeOccupied() {
        return target == Target.OCCUPIED;
    }

    /**
     * Set a constraint that must be matched after the original has been validated.
     *
     * @param then The constraint to be set.
     * @return The original constraint chained with the new one.
     */
    public Constraint then(final Constraint then) {
        this.then = then;

        return this;
    }

    /**
     * Get the next chained constraint.
     *
     * @return The chained constraint.
     */
    public Constraint getNext() {
        return then;
    }

    /**
     * Check whether a chained constraint exists.
     *
     * @return A boolean.
     */
    public boolean hasNext() {
        return then != null;
    }

    /**
     * The possible directions that can be constrained.
     */
    public enum Direction {
        HORIZONTAL, VERTICAL, DIAGONAL, FORWARD, BACKWARD, DIAGONAL_FORWARD, DIAGONAL_BACKWARD, ANY
    }

    /**
     * Whether the target field must be occupied or empty.
     */
    public enum Target {
        EMPTY, OCCUPIED, EMPTY_OR_OCCUPIPED
    }
}
