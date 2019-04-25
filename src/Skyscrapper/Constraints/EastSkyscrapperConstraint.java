package Skyscrapper.Constraints;

import BoardGames.Board;

public class EastSkyscrapperConstraint extends SkyscrapperConstraint {
    public EastSkyscrapperConstraint(int line, int visibleAmount) {
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

        for (int i = dimension - 1; i > 0; i--) {
            if (checkLine(i, board)) {
                currentlyVisibleAmount += 1;
            }
        }

        return currentlyVisibleAmount == visibleAmount;
    }

    public boolean checkLine(int skyscrapperPosition, Board board) {
        int current;
        int following;

        int dimension = board.getDimension();

        current = board.get(line, skyscrapperPosition);

        for (int i = skyscrapperPosition; i < dimension; i++) {
            following = board.get(line, i + 1);

            if (current == 0) {
                return true;
            }

            if (current < following) {
                return false;
            }
        }

        return true;
    }
}
