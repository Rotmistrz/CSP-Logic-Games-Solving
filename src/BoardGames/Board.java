package BoardGames;

import Coordinates.Point;

import java.util.Iterator;
import java.util.LinkedList;

public class Board {
    private static final int NEUTRAL_VALUE = 0;

    private BoardChecker checker;
    private int dimension;
    private int[][] board;

    private LinkedList<BoardConstraint> constraints;
    private LinkedList<Point> constantPoints;

    public Board(int dimension, BoardChecker checker) {
        this.dimension = dimension;
        this.checker = checker;

        this.board = new int[this.dimension][this.dimension];

        this.reset();

        this.constraints = new LinkedList<BoardConstraint>();
        this.constantPoints = new LinkedList<Point>();
    }

    public Board setBoard(int[][] values) {
        this.board = values;

        this.dimension = this.board.length;

        return this;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int getDimension() {
        return this.dimension;
    }

    public Board reset() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = NEUTRAL_VALUE;
            }
        }

        return this;
    }

    public int get(int x, int y) {
        int arrayX = x - 1;
        int arrayY = y - 1;

        return this.board[arrayX][arrayY];
    }

    public int getByIndex(int x, int y) {
        return get(x + 1, y + 1);
    }

    public boolean set(int x, int y, int value) {
        if (!isPointConstant(new Point(x, y))) {
            int arrayX = x - 1;
            int arrayY = y - 1;

            this.board[arrayX][arrayY] = value;

            return true;
        } else {
            return false;
        }
    }

    public boolean setByIndex(int x, int y, int value) {
        return set(x + 1, y + 1, value);
    }

    public Board setConstraint(BoardConstraint constraint) {
        this.constraints.add(constraint);

        return this;
    }

    public LinkedList<BoardConstraint> getConstraints() {
        return this.constraints;
    }

    public boolean check() {
        return checker.check(this);
    }

    public boolean isValueAllowed(int x, int y, int value) {
        int recent = get(x, y);

        this.set(x, y, value);

        if (this.check()) {
            this.set(x, y, recent);

            return true;
        } else {
            this.set(x, y, recent);

            return false;
        }
    }

    public boolean isPointConstant(Point point) {
        Iterator<Point> it = constantPoints.iterator();
        Point current;

        while (it.hasNext()) {
            current = it.next();

            if (current.equals(point)) {
                return true;
            }
        }

        return false;
    }

    public LinkedList<Point> getConstantPoints() {
        return this.constantPoints;
    }

    public Board setConstantPoint(Point point) {
        this.constantPoints.add(point);

        return this;
    }

    public Board makeCurrentBoardConstant() {
        int current;

        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                current = getByIndex(i, j);

                if (current != NEUTRAL_VALUE) {
                    setConstantPoint(new Point(i + 1, j + 1));
                }
            }
        }

        return this;
    }

    public boolean isComplete() {
        int value;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                value = getByIndex(i, j);

                if (value == NEUTRAL_VALUE) {
                    return false;
                }
            }
        }

        return true;
    }

    public void present() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(getByIndex(i, j) + " ");
            }

            System.out.println();
        }
    }
}
