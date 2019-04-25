package Skyscrapper;

import BoardGames.Board;
import BoardGames.BoardChecker;
import Skyscrapper.Constraints.EastSkyscrapperConstraint;
import Skyscrapper.Constraints.NorthSkyscrapperConstraint;
import Skyscrapper.Constraints.SkyscrapperConstraint;
import Skyscrapper.Constraints.WestSkyscrapperConstraint;
import junit.framework.TestCase;

public class SkyscrapperConstraintTest extends TestCase {
    public void testCheck() {
        int[][] values = {
                {1, 3, 2, 4},
                {2, 1, 4, 3},
                {4, 2, 3, 1},
                {3, 4, 1, 2}
        };

        SkyscrapperConstraint constraint01 = new NorthSkyscrapperConstraint(1, 3);
        SkyscrapperConstraint constraint01false = new NorthSkyscrapperConstraint(1, 2);

        BoardChecker checker = new BoardChecker();

        Board board = new Board(4, checker);
        board.setBoard(values);

        assertTrue(constraint01.check(board));
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(1);
        assertFalse(constraint01false.check(board));

        constraint01false.setVisibleAmount(4);
        assertFalse(constraint01false.check(board));

        SkyscrapperConstraint constraint03 = new WestSkyscrapperConstraint(1, 3);
        SkyscrapperConstraint constraint03false = new WestSkyscrapperConstraint(1, 2);

        assertTrue(constraint03.check(board));
        assertFalse(constraint03false.check(board));

        constraint03false.setVisibleAmount(1);
        assertFalse(constraint03false.check(board));

        constraint03false.setVisibleAmount(4);
        assertFalse(constraint03false.check(board));


        SkyscrapperConstraint constraint04 = new EastSkyscrapperConstraint(1, 1);
        SkyscrapperConstraint constraint04false = new EastSkyscrapperConstraint(1, 2);

        assertTrue(constraint04.check(board));
        assertFalse(constraint04false.check(board));

        constraint04false.setVisibleAmount(3);
        assertFalse(constraint04false.check(board));

        constraint04false.setVisibleAmount(4);
        assertFalse(constraint04false.check(board));
    }
}