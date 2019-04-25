package Skyscrapper.Constraints;

import BoardGames.Board;

public class WestSkyscrapperConstraint extends SkyscrapperConstraint {
    public WestSkyscrapperConstraint(int line, int visibleAmount) {
        super(line, visibleAmount);
    }

    @Override
    public boolean check(Board board) {
        int dimension = board.getDimension();

        int currentlyVisibleAmount = 1;

        int current;

        for (int i = 1; i <= dimension; i++) {
            current = board.get(i, line);

            if (current == 0) {
                return true;
            }
        }

        for (int i = 2; i <= dimension; i++) {
            if (checkLine(i, board)) {
                currentlyVisibleAmount += 1;
            }
        }

        return currentlyVisibleAmount == visibleAmount;
    }

    public boolean checkLine(int skyscrapperPosition, Board board) {
        int current;
        int following;

        current = board.get(line, skyscrapperPosition);

        if (current == 0) {
            return true;
        }

        for (int i = skyscrapperPosition; i > 1; i--) {
            following = board.get(line, i - 1);

            if (current < following) {
                return false;
            }
        }

        return true;
    }
}
