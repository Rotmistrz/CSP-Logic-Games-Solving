package Futoshiki;

import BoardGames.Board;
import BoardGames.BoardChecker;
import BoardGames.BoardConstraint;

import java.util.Iterator;
import java.util.LinkedList;

public class FutoshikiChecker extends BoardChecker {
/**    private static final int NEUTRAL_VALUE = 0;

    private LinkedList<Integer> usedValuesX;
    private LinkedList<Integer> usedValuesY;

    public FutoshikiChecker() {
        this.usedValuesX = new LinkedList<Integer>();
        this.usedValuesY = new LinkedList<Integer>();
    }

    public boolean isNeutral(int value) {
        return value == NEUTRAL_VALUE;
    }

    @Override
    public boolean check(Board board) {
        int dimension = board.getDimension();
        int currentX;
        int currentY;

        LinkedList<BoardConstraint> constraints = board.getConstraints();

        Iterator<BoardConstraint> it = constraints.iterator();
        BoardConstraint constraint;

        while (it.hasNext()) {
            constraint = it.next();

            if (!constraint.check(board)) {
                return false;
            }
        }

        for (int i = 1; i <= dimension; i++) {
            this.usedValuesX.clear();
            this.usedValuesY.clear();

            for (int j = 1; j <= dimension; j++) {
                currentX = board.get(i, j);
                currentY = board.get(j, i);

                if (this.usedValuesX.contains(currentX) || this.usedValuesY.contains(currentY)) {
                    return false;
                }

                if (!isNeutral(currentX)) {
                    this.usedValuesX.add(currentX);
                }

                if (!isNeutral(currentY)) {
                    this.usedValuesY.add(currentY);
                }
            }
        }

        return true;
    }**/
}
