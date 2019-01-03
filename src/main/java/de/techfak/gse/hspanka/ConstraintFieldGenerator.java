package de.techfak.gse.hspanka;

import de.techfak.gse.hspanka.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generates fields that match constraints.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class ConstraintFieldGenerator {

    /**
     * The constraints of which at least ONE must be met.
     */
    private List<Constraint> constraints = new ArrayList<>();

    /**
     * The constraints of which at least ONE must be met and the srcPiece must be untouched.
     */
    private List<Constraint> untouchedConstraints = new ArrayList<>();

    /**
     * The generated fields that match the constraints.
     */
    private transient boolean[][] fields = new boolean[Board.FIELD_SIZE][Board.FIELD_SIZE];

    /**
     * The srcPiece that is being moved.
     */
    private transient Piece srcPiece;

    /**
     * Add a new constraint to the field generator.
     *
     * @param constraint The constraint to be added.
     * @return The generator with the added constraint.
     */
    public ConstraintFieldGenerator addConstraint(final Constraint constraint) {
        constraints.add(constraint);

        return this;
    }

    /**
     * Add a new untouched constraint to the field generator.
     * The constraint will only be validated if the srcPiece has not been touched yet.
     *
     * @param constraint The constraint to be added.
     * @return The generator with the added constraint.
     */
    public ConstraintFieldGenerator addUntouchedConstraint(final Constraint constraint) {
        untouchedConstraints.add(constraint);

        return this;
    }

    /**
     * returns an array that indicates whether a field matches the constraints.
     *
     * @return All the fields of the board with matched fields set to true.
     */
    public boolean[][] getFields() {
        return fields.clone();
    }

    private void reset() {
        for (final boolean[] fieldRow : fields) {
            Arrays.fill(fieldRow, Boolean.FALSE);
        }
    }

    /**
     * Generates and returns an array that indicates whether a field matches the constraints.
     *
     * @param move   The move for which the fields should be generated.
     * @param pieces The pieces on the board.
     * @return All the fields of the board with matched fields set to true.
     */
    public boolean[][] getFields(final Move move, final Piece[][] pieces) {
        reset();

        final int col = move.getcFrom();
        final int row = move.getrFrom();

        srcPiece = pieces[row][col];

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

    private void processConstraint(final int col, final int row, final Constraint constraint, final Piece[][] pieces) {
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
                // If the White Player is moving a srcPiece we need to flip the board.
                if (srcPiece.getPlayer() == Player.WHITE) {
                    generateDiagonalBackward(col, row, constraint, pieces);
                } else {
                    generateDiagonalForward(col, row, constraint, pieces);
                }
                break;
            case DIAGONAL_BACKWARD:
                // If the White Player is moving a srcPiece we need to flip the board.
                if (srcPiece.getPlayer() == Player.WHITE) {
                    generateDiagonalForward(col, row, constraint, pieces);
                } else {
                    generateDiagonalBackward(col, row, constraint, pieces);
                }
                break;
            case FORWARD:
                // If the White Player is moving a srcPiece we need to flip the board.
                if (pieces[row][col].getPlayer() == Player.WHITE) {
                    generateBackward(col, row, constraint, pieces);
                } else {
                    generateForward(col, row, constraint, pieces);
                }
                break;
            case BACKWARD:
                // If the White Player is moving a srcPiece we need to flip the board.
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

    private void generateForward(final int col, final int row, final Constraint constraint, final Piece[][] pieces) {
        for (int i = 1; row + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row + i][col] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row + i][col] == null) {
                    continue;
                }

                if (constraint.hasNext()) {
                    processConstraint(col, row + i, constraint.getNext(), pieces);
                } else {
                    fields[row + i][col] = true;
                }
            }

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row + i][col] != null) {
                break;
            }
        }
    }

    private void generateBackward(final int col, final int row, final Constraint constraint, final Piece[][] pieces) {
        for (int i = 1; row - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row - i][col] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row - i][col] == null) {
                    continue;
                }

                if (constraint.hasNext()) {
                    processConstraint(col, row - i, constraint.getNext(), pieces);
                } else {
                    fields[row - i][col] = true;
                }
            }

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row - i][col] != null) {
                break;
            }
        }
    }

    private void generateVerticalForward(
        final int col,
        final int row,
        final Constraint constraint,
        final Piece[][] pieces) {
        for (int i = 1; col + i < Board.FIELD_SIZE && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row][col + i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row][col + i] == null) {
                    continue;
                }

                if (constraint.hasNext()) {
                    processConstraint(col + i, row, constraint.getNext(), pieces);
                } else {
                    fields[row][col + i] = true;
                }
            }

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row][col + i] != null) {
                break;
            }
        }
    }

    private void generateVerticalBackward(
        final int col,
        final int row,
        final Constraint constraint,
        final Piece[][] pieces) {
        for (int i = 1; col - i >= 0 && i <= constraint.getMax(); i++) {
            if (i >= constraint.getMin()) {
                if (constraint.targetFieldMustBeEmpty() && pieces[row][col - i] != null) {
                    continue;
                }

                if (constraint.targetFieldMustBeOccupied() && pieces[row][col - i] == null) {
                    continue;
                }

                if (constraint.hasNext()) {
                    processConstraint(col - i, row, constraint.getNext(), pieces);
                } else {
                    fields[row][col - i] = true;
                }
            }

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row][col - i] != null) {
                break;
            }
        }
    }

    private void generateDiagonalForward(
        final int col,
        final int row,
        final Constraint constraint,
        final Piece[][] pieces) {
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

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row + i][col + i] != null) {
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

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row + i][col - i] != null) {
                break;
            }
        }
    }

    private void generateDiagonalBackward(
        final int col,
        final int row,
        final Constraint constraint,
        final Piece[][] pieces) {
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

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row - i][col + i] != null) {
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

            if (constraint.fieldsInBetweenMustBeEmpty() && pieces[row - i][col - i] != null) {
                break;
            }
        }
    }
}
