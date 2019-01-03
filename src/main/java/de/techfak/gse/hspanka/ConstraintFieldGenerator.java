package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.exceptions.InvalidMoveException;
import de.techfak.gse.hspanka.piece.Piece;

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
     * The constraints of which at least ONE must be met and the piece must be untouched.
     */
    private List<Constraint> untouchedConstraints = new ArrayList<>();

    /**
     * The generated fields that match the constraints.
     */
    private transient boolean[][] fields = new boolean[Board.FIELD_SIZE][Board.FIELD_SIZE];

    /**
     * Add a constraint.
     *
     * @param direction The direction to be constrained.
     * @param min       The minimum steps into the direction.
     * @param max       The maximum steps into the direction.
     * @param required  Whether the constraint must ALWAYS match.
     * @return The current ConstraintFieldGenerator instance with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(
        final Direction direction,
        final int min,
        final int max,
        final boolean untouched
    ) {
        return addConstraint(direction, min, max, untouched, true);
    }

    /**
     * Add a constraint.
     *
     * @param direction The direction to be constrained.
     * @param min       The minimum steps into the direction.
     * @param max       The maximum steps into the direction.
     * @param required  Whether the constraint must ALWAYS match.
     * @param empty     Whether the fields in between must be empty.
     * @return The current ConstraintFieldGenerator instance with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(
        final Direction direction,
        final int min,
        final int max,
        final boolean untouched,
        final boolean empty
    ) {
        final Constraint constraint = new Constraint(direction, min, max, empty, false, false);

        if (untouched) {
            untouchedConstraints.add(constraint);
        } else {
            constraints.add(constraint);
        }

        return this;
    }

    /**
     * Add a constraint.
     *
     * @param direction The direction to be constrained.
     * @param min       The minimum steps into the direction.
     * @param max       The maximum steps into the direction.
     * @param required  Whether the constraint must ALWAYS match.
     * @param empty     Whether the fields in between must be empty.
     * @param targetEmpty Whether the target field must be empty.
     * @return The current ConstraintFieldGenerator instance with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(
        final Direction direction,
        final int min,
        final int max,
        final boolean untouched,
        final boolean empty,
        final boolean targetEmpty
    ) {
        final Constraint constraint = new Constraint(direction, min, max, empty, targetEmpty, false);

        if (untouched) {
            untouchedConstraints.add(constraint);
        } else {
            constraints.add(constraint);
        }

        return this;
    }

    /**
     * Add a constraint.
     *
     * @param direction The direction to be constrained.
     * @param min       The minimum steps into the direction.
     * @param max       The maximum steps into the direction.
     * @param required  Whether the constraint must ALWAYS match.
     * @param empty     Whether the fields in between must be empty.
     * @param targetEmpty Whether the target field must be empty.
     * @param targetOccupied Whether the target field must be occupied.
     * @return The current ConstraintFieldGenerator instance with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(
        final Direction direction,
        final int min,
        final int max,
        final boolean untouched,
        final boolean empty,
        final boolean targetEmpty,
        final boolean targetOccupied
    ) {
        final Constraint constraint = new Constraint(direction, min, max, empty, targetEmpty, targetOccupied);

        if (untouched) {
            untouchedConstraints.add(constraint);
        } else {
            constraints.add(constraint);
        }

        return this;
    }

    /**
     * returns an array that indicates whether a field matches the constraints.
     *
     * @return All the fields of the board with matched fields set to true.
     */
    public boolean[][] getFields() {
        return fields;
    }

    private void reset() {
        for (final boolean[] fieldRow : fields) {
            Arrays.fill(fieldRow, Boolean.FALSE);
        }
    }

    /**
     * Generates and returns an array that indicates whether a field matches the constraints.
     *
     * @param col    The column that should be checked.
     * @param row    the row that should be checked.
     * @param pieces The pieces on the board.
     * @return All the fields of the board with matched fields set to true.
     * @throws InvalidMoveException
     */
    public boolean[][] getFields(Move move, Piece[][] pieces) {
        reset();

        int col = move.getcFrom();
        int row = move.getrFrom();

        for (final Constraint constraint : constraints) {
            processConstraint(col, row, constraint, pieces);
        }

        if (!pieces[row][col].hasBeenTouched()) {
            for (final Constraint constraint : untouchedConstraints) {
                processConstraint(col, row, constraint, pieces);
            }
        }

        return fields.clone();
    }

    private void processConstraint(int col, int row, Constraint constraint, Piece[][] pieces) {
        switch (constraint.getDirection()) {
            case ANY:
                generateAny();
                break;
            case HORIZONTAL:
                generateForward(col, row, constraint, pieces);
                generateBackward(col, row, constraint, pieces);
                break;
            case VERTICAL:
                generateVerticalForward(col, row, constraint, pieces);
                generateVerticalBackward(col, row, constraint, pieces);
                break;
            case DIAGONAL:
                generateDiagonalForward(col, row, constraint, pieces);
                generateDiagonalBackward(col, row, constraint, pieces);
                break;
            case DIAGONAL_FORWARD:
                // If the White Player is moving a piece we need to flip the board.
                if (pieces[row][col].getPlayer() == Player.WHITE) {
                    generateDiagonalBackward(col, row, constraint, pieces);
                } else {
                    generateDiagonalForward(col, row, constraint, pieces);
                }
                break;
            case DIAGONAL_BACKWARD:
                // If the White Player is moving a piece we need to flip the board.
                if (pieces[row][col].getPlayer() == Player.WHITE) {
                    generateDiagonalForward(col, row, constraint, pieces);
                } else {
                    generateDiagonalBackward(col, row, constraint, pieces);
                }
                break;
            case FORWARD:
                // If the White Player is moving a piece we need to flip the board.
                if (pieces[row][col].getPlayer() == Player.WHITE) {
                    generateBackward(col, row, constraint, pieces);
                } else {
                    generateForward(col, row, constraint, pieces);
                }
                break;
            case BACKWARD:
                // If the White Player is moving a piece we need to flip the board.
                if (pieces[row][col].getPlayer() == Player.WHITE) {
                    generateForward(col, row, constraint, pieces);
                } else {
                    generateBackward(col, row, constraint, pieces);
                }
                break;

            default:
                //
        }
    }

    private void generateAny() {
        for (final boolean[] fieldRow : fields) {
            Arrays.fill(fieldRow, Boolean.TRUE);
        }
    }

    private void generateForward(final int col, final int row, final Constraint constraint, Piece[][] pieces) {
        for (int i = 1; row + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row + i][col] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row + i][col] == null) {
                    continue;
                }

                fields[row + i][col] = true;
            }

            if (constraint.getEmpty() && pieces[row + i][col] != null) {
                break;
            }
        }
    }

    private void generateBackward(final int col, final int row, final Constraint constraint, Piece[][] pieces) {
        for (int i = 1; row - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row - i][col] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row - i][col] == null) {
                    continue;
                }

                fields[row - i][col] = true;
            }

            if (constraint.getEmpty() && pieces[row - i][col] != null) {
                break;
            }
        }
    }

    private void generateVerticalForward(final int col, final int row, final Constraint constraint, Piece[][] pieces) {
        for (int i = 1; col + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row][col + i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row][col + i] == null) {
                    continue;
                }

                fields[row][col + i] = true;
            }

            if (constraint.getEmpty() && pieces[row][col + i] != null) {
                break;
            }
        }
    }

    private void generateVerticalBackward(final int col, final int row, final Constraint constraint, Piece[][] pieces) {
        for (int i = 1; col - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row][col - i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row][col - i] == null) {
                    continue;
                }

                fields[row][col - i] = true;
            }

            if (constraint.getEmpty() && pieces[row][col - i] != null) {
                break;
            }
        }
    }

    private void generateDiagonalForward(final int col, final int row, final Constraint constraint, Piece[][] pieces ) {
        for (int i = 1; col + i < Board.FIELD_SIZE && row + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row + i][col + i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row + i][col + i] == null) {
                    continue;
                }

                fields[row + i][col + i] = true;
            }

            if (constraint.getEmpty() && pieces[row + i][col + i] != null) {
                break;
            }
        }

        for (int i = 1; col - i >= 0 && row + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row + i][col - i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row + i][col - i] == null) {
                    continue;
                }

                fields[row + i][col - i] = true;
            }

            if (constraint.getEmpty() && pieces[row + i][col - i] != null) {
                break;
            }
        }
    }

    private void generateDiagonalBackward(final int col, final int row, final Constraint constraint, Piece[][] pieces) {
        for (int i = 1; col + i < Board.FIELD_SIZE && row - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row - i][col + i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row - i][col + i] == null) {
                    continue;
                }

                fields[row - i][col + i] = true;
            }

            if (constraint.getEmpty() && pieces[row - i][col + i] != null) {
                break;
            }
        }

        for (int i = 1; col - i >= 0 && row - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row - i][col - i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row - i][col - i] == null) {
                    continue;
                }

                fields[row - i][col - i] = true;
            }

            if (constraint.getEmpty() && pieces[row - i][col - i] != null) {
                break;
            }
        }
    }
}
