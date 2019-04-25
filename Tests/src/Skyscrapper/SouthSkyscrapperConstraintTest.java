package Skyscrapper;

import BoardGames.Board;
import BoardGames.BoardChecker;
import Skyscrapper.Constraints.SkyscrapperConstraint;
import Skyscrapper.Constraints.SouthSkyscrapperConstraint;
import junit.framework.TestCase;

public class SouthSkyscrapperConstraintTest extends TestCase {
    public void testCheck() {
        int[][] values = {
                {1, 3, 2, 4},
                {2, 1, 4, 3},
                {4, 2, 3, 1},
                {3, 4, 1, 2}
        };

        SkyscrapperConstraint constraint01 = new SouthSkyscrapperConstraint(3, 3);
        SkyscrapperConstraint constraint01false = new SouthSkyscrapperConstraint(3, 2);

        BoardChecker checker = new BoardChecker();

        Board board = new Board(4, checker);
        board.setBoard(values);

        assertTrue(constraint01.check(board));
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(1);
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(4);
        assertFalse(constraint01false.check(board));

        constraint01.setLine(4);
        constraint01.setVisibleAmount(3);
        assertTrue(constraint01.check(board));

        constraint01false.setVisibleAmount(4);
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(2);
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(1);
        assertFalse(constraint01false.check(board));

        int[][] values02 = {
                {1, 3, 2, 4},
                {2, 1, 0, 3},
                {4, 2, 3, 1},
                {3, 4, 1, 2}
        };

        board.setBoard(values02);

        constraint01false.setVisibleAmount(4);
        assertTrue(constraint01false.check(board));
    }
}