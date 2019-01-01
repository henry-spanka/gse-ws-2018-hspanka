package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.techfak.gse.hspanka.Constraint.Direction;

/**
 * Generates fields that match constraints.
 */
public class ConstraintFieldGenerator {

    /**
     * The constraints of which at least ONE must be met.
     */
    private List<Constraint> constraints = new ArrayList<>();

    /**
     * The constraints of which ALL must be met.
     */
    private List<Constraint> requiredConstraints = new ArrayList<>();

    /**
     * The generated fields that match the constraints.
     */
    private transient boolean[][] fields;

    /**
     * Add a constraint.
     * @param direction The direction to be constrained.
     * @param min The minimum steps into the direction.
     * @param max The maximum steps into the direction.
     * @param required Whether the constraint must ALWAYS match.
     * @return The current ConstraintFieldGenerator instance with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(final Direction direction, final int min, final int max, final boolean required) {
        final Constraint constraint = new Constraint(direction, min, max);

        if (required) {
            requiredConstraints.add(constraint);
        } else {
            constraints.add(constraint);
        }

        return this;
    }

    /**
     * Generates and returns an array that indicates whether a field matches the constraints.
     * @param col The column that should be checked.
     * @param row the row that should be checked.
     * @return All the fields of the board with matched fields set to true.
     * @throws InvalidMoveException
     */
    public boolean[][] getFields(final int col, final int row) {
        fields = new boolean[Board.FIELD_SIZE][Board.FIELD_SIZE];

        if (constraints.isEmpty()) {
            for (final boolean[] fieldRow : fields) {
                Arrays.fill(fieldRow, Boolean.TRUE);
            }

            return fields.clone();
        }

        for (final Constraint constraint : constraints) {
            switch (constraint.getDirection()) {
                case ANY:
                    generateForward(col, row, constraint);
                    generateBackward(col, row, constraint);
                    generateVerticalForward(col, row, constraint);
                    generateVerticalBackward(col, row, constraint);
                    generateDiagonal(col, row, constraint);
                    break;

                default:
                    //
            }
        }

        return fields.clone();
    }

    private void generateForward(final int col, final int row, final Constraint constraint) {
        int i = constraint.getMin();

        while (row + i < Board.FIELD_SIZE && i <= constraint.getMax()) {
            fields[row + i][col] = true;

            i++;
        }
    }

    private void generateBackward(final int col, final int row, final Constraint constraint) {
        int i = constraint.getMin();

        while (row - i >= 0 && i <= constraint.getMax()) {
            fields[row - i][col] = true;

            i++;
        }
    }

    private void generateVerticalForward(final int col, final int row, final Constraint constraint) {
        int i = constraint.getMin();

        while (col + i < Board.FIELD_SIZE && i <= constraint.getMax()) {
            fields[row][col+ i] = true;

            i++;
        }
    }

    private void generateVerticalBackward(final int col, final int row, final Constraint constraint) {
        int i = constraint.getMin();

        while (col - i >= 0 && i <= constraint.getMax()) {
            fields[row][col - i] = true;

            i++;
        }
    }

    private void generateDiagonal(final int col, final int row, final Constraint constraint) {
        int i = constraint.getMin();

        while (col + i < Board.FIELD_SIZE && row + i < Board.FIELD_SIZE && i <= constraint.getMax()) {
            fields[row + i][col + i] = true;

            i++;
        }

        i = constraint.getMin();

        while (col - i >= 0 && row - i >= 0 && i <= constraint.getMax()) {
            fields[row - i][col - i] = true;

            i++;
        }

        i = constraint.getMin();

        while (col - i >= 0 && row + i < Board.FIELD_SIZE && i <= constraint.getMax()) {
            fields[row + i][col - i] = true;

            i++;
        }

        i = constraint.getMin();

        while (col + i < Board.FIELD_SIZE && row - i >= 0 && i <= constraint.getMax()) {
            fields[row - i][col + i] = true;

            i++;
        }
    }
}
