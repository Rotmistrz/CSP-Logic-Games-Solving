package Futoshiki;

import BoardGames.Board;
import BoardGames.BoardConstraint;
import Coordinates.Point;

public class InequalityConstraint implements BoardConstraint {
    private static final int NEUTRAL_VALUE = 0;

    private Point smaller;
    private Point greater;

    public InequalityConstraint(Point smaller, Point greater) {
        this.smaller = smaller;
        this.greater = greater;
    }

    @Override
    public boolean check(Board board) {
        int smallerValue = board.get(smaller.getX(), smaller.getY());
        int greaterValue = board.get(greater.getX(), greater.getY());

        if (isNeutral(smallerValue) || isNeutral(greaterValue)) {
            return true;
        } else {
            return smallerValue < greaterValue;
        }
    }

    public boolean isNeutral(int value) {
        return value == NEUTRAL_VALUE;
    }
}