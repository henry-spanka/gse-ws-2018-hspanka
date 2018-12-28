package de.techfak.gse.hspanka;

/**
 * Class defines a constraint that can be verified.
 */
public class Constraint {
    /**
     * The possible directions that can be constrained.
     */
    public enum Direction {
        HORIZONTAL, VERTICAL, DIAGONAL, FORWARD, BACKWARD, ANY
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
     * Instaniates a new constraint.
     * @param direction The direction to be enforced.
     * @param min The minimium step size.
     * @param max The maximum step size.
     */
    public Constraint(Direction direction, int min, int max) {
        this.direction = direction;
        this.min = min;
        this.max = max;
    }

    /**
     * Get the direction to be enforced
     * @return The direction
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
}
