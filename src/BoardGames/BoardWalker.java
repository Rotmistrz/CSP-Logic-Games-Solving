package BoardGames;

import Coordinates.Point;

import java.util.LinkedList;

abstract public class BoardWalker {
    protected int dimension;

    public BoardWalker(int dimension) {
        this.dimension = dimension;
    }

    abstract public Point forward();
    abstract public Point backward();
    abstract public BoardWalker start();
    abstract public BoardWalker end();

    abstract public boolean canForward();
    abstract public boolean canBackward();

    abstract public BoardWalker setConstantPoint(Point point);
    abstract public boolean isPointConstant(Point point);
    abstract public BoardWalker setConstantPointsList(LinkedList<Point> points);
}
