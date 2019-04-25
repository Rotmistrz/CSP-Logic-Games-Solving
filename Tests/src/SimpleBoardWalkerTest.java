import BoardGames.SimpleBoardWalker;
import Coordinates.Point;
import junit.framework.TestCase;

public class SimpleBoardWalkerTest extends TestCase {
    public void testForward() {
        int[][] expectedBoard = {
                {
                    1, 2, 3, 4
                },
                {
                    5, 6, 7, 8
                },
                {
                    9, 10, 11, 12
                },
                {
                    13, 14, 15, 16
                }
        };

        int[][] actualBoard = new int[4][4];

        int dimension = expectedBoard.length;

        SimpleBoardWalker walker = new SimpleBoardWalker(dimension);

        int boardLength = dimension * dimension;

        Point current;
        int x;
        int y;

        for (int i = 0; i < boardLength && walker.canForward(); i++) {
            current = walker.forward();

            System.out.println(current + " = " + (i + 1));

            x = current.getX() - 1;
            y = current.getY() - 1;

            actualBoard[x][y] = (i + 1);
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                assertEquals(expectedBoard[i][j], actualBoard[i][j]);
            }
        }

        int[][] anotherExpectedBoard = {
                {
                        1, 2, 0, 3
                },
                {
                        4, 5, 0, 6
                },
                {
                        7, 0, 8, 9
                },
                {
                        10, 11, 12, 13
                }
        };

        int[][] anotherActualBoard = {
                {
                        0, 0, 0, 0
                },
                {
                        0, 0, 0, 0
                },
                {
                        0, 0, 0, 0
                },
                {
                        0, 0, 0, 0
                }
        };

        walker.start();
        walker.setConstantPoint(new Point(1, 3));
        walker.setConstantPoint(new Point(2, 3));
        walker.setConstantPoint(new Point(3, 2));

        int n = 0;

        while (walker.canForward()) {
            current = walker.forward();

            System.out.println(current + " = " + (n + 1));

            x = current.getX() - 1;
            y = current.getY() - 1;

            anotherActualBoard[x][y] = (n + 1);
            n++;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                assertEquals(anotherExpectedBoard[i][j], anotherActualBoard[i][j]);
            }
        }
    }

    public void testBackward() {
        int[][] expectedBoard = {
                {
                        1, 2, 3, 4
                },
                {
                        5, 6, 7, 8
                },
                {
                        9, 10, 11, 12
                },
                {
                        13, 14, 15, 16
                }
        };

        int dimension = expectedBoard.length;
        int boardLength = dimension * dimension;

        int[][] actualBoard = new int[dimension][dimension];

        SimpleBoardWalker walker = new SimpleBoardWalker(dimension);

        walker.end();

        Point current;
        int x;
        int y;

        for (int i = 16; i > 0 && walker.canBackward(); i--) {
            current = walker.backward();

            System.out.println(current + " = " + i);

            x = current.getX() - 1;
            y = current.getY() - 1;

            actualBoard[x][y] = i;
        }

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                assertEquals(expectedBoard[i][j], actualBoard[i][j]);
            }
        }
    }
}
