package de.techfak.gse.hspanka;

/**
 * Class defines a constraint that can be verified.
 */
public class Constraint {
    /**
     * The possible directions that can be constrained.
     */
    public enum Direction {
        HORIZONTAL, VERTICAL, DIAGONAL, FORWARD, BACKWARD, DIAGONAL_FORWARD, DIAGONAL_BACKWARD, ANY
    }

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
     * Whether the target field must be empty.
     */
    private boolean targetEmpty;

    /**
     * Whether the target field must be occupied.
     */
    private boolean targetOccupied;

    /**
     * Instaniates a new constraint.
     * @param direction The direction to be enforced.
     * @param min The minimium step size.
     * @param max The maximum step size.
     */
    public Constraint(final Direction direction, final int min, final int max, final boolean empty, final boolean targetEmpty, final boolean targetOccupied) {
        this.direction = direction;
        this.min = min;

        if (max == -1) {
            this.max = Board.FIELD_SIZE - 1;
        } else {
            this.max = max;
        }

        this.empty = empty;
        this.targetEmpty = targetEmpty;
        this.targetOccupied = targetOccupied;
    }

    /**
     * Get the direction to be enforced.
     * @return The direction,
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get minimum step size.
     * @return The minimum step size.
     */
    public int getMin() {
        return min;
    }

    /**
     * Get the maximum step size.
     * @return The maximum step size.
     */
    public int getMax() {
        return max;
    }

    /**
     * Whether the fields in between must be empty.
     * @return True if they must be empty.
     */
    public boolean getEmpty() {
        return empty;
    }

    /**
     * Whether the target field must be empty.
     * @return True if it must be empty.
     */
    public boolean targetFieldMustBeEmpty() { return targetEmpty; }

    /**
     * Whether the target field must be occupied.
     */
    public boolean targetFieldMustBeOccupied() {
        return targetOccupied;
    }
}
