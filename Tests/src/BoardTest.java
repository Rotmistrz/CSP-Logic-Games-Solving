import BoardGames.Board;
import BoardGames.BoardChecker;
import Coordinates.Point;
import Futoshiki.InequalityConstraint;
import junit.framework.TestCase;

public class BoardTest extends TestCase {
    public void testCheck() {
        BoardChecker checker = new BoardChecker();

        Board board = new Board(4, checker);

        int[][] values = {
                { 1, 3, 2, 4 },
                { 2, 1, 4, 3 },
                { 4, 2, 3, 1 },
                { 3, 4, 1, 2 }
        };

        board.setBoard(values);
        board.setConstraint(new InequalityConstraint(new Point(4, 1), new Point(3, 1)));

        assertTrue(board.check());

        int[][] values2 = {
                { 1, 3, 2, 4 },
                { 2, 1, 1, 3 },
                { 2, 2, 3, 1 },
                { 3, 4, 1, 2 }
        };

        board.setBoard(values2);

        assertFalse(board.check());

        int[][] values3 = {
                { 1, 3, 2, 4 },
                { 2, 1, 4, 3 },
                { 4, 2, 3, 1 },
                { 3, 4, 1, 2 }
        };

        board.setBoard(values3);

        board.setConstraint(new InequalityConstraint(new Point(1, 2), new Point(2, 2)));

        assertFalse(board.check());

        int[][] values4 = {
                { 1, 3, 0, 0 },
                { 2, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 3, 0, 0, 0 }
        };

        board.setBoard(values4);

        assertTrue(board.check());

        int[][] values5 = {
                { 1, 2, 4, 3 },
                { 3, 1, 2, 4 },
                { 2, 3, 1, 3 },
                { 4, 0, 0, 0 }
        };

        board.setBoard(values5);

        assertFalse(board.check());
    }

    public void testIsPointConstant() {
        int[][] values = {
                { 1, 3, 2, 4 },
                { 2, 1, 4, 3 },
                { 4, 2, 3, 1 },
                { 3, 4, 1, 2 }
        };

        BoardChecker checker = new BoardChecker();
        int dimension = values.length;

        Board board = new Board(dimension, checker);

        Point firstConstant = new Point(2, 2);
        Point secondConstant = new Point(2, 4);
        Point nonConstant = new Point(2, 3);

        board.setConstantPoint(firstConstant);
        board.setConstantPoint(secondConstant);

        assertTrue(board.isPointConstant(firstConstant));
        assertTrue(board.isPointConstant(secondConstant));
        assertFalse(board.isPointConstant(nonConstant));

        assertFalse(board.set(firstConstant.getX(), firstConstant.getY(), 2));
        assertFalse(board.set(secondConstant.getX(), secondConstant.getY(), 4));
        assertTrue(board.set(nonConstant.getX(), nonConstant.getY(), 3));
    }
}
