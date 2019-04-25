package BoardGames;

import Coordinates.Point;

import java.util.Iterator;
import java.util.LinkedList;

public class SimpleBoardWalker extends BoardWalker {
    private int x;
    private int y;

    private LinkedList<Point> constantPoints;

    public SimpleBoardWalker(int dimension) {
        super(dimension);

        this.start();

        this.constantPoints = new LinkedList<Point>();
    }

    public SimpleBoardWalker setConstantPointsList(LinkedList<Point> points) {
        this.constantPoints = points;

        return this;
    }

    private boolean isRowEnded() {
        return this.y >= this.dimension;
    }

    private boolean isRowStarted() {
        return this.y <= 1;
    }

    private boolean isColumnEnded() {
        return this.x >= this.dimension;
    }

    private boolean isColumnStarted() {
        return this.x <= 1;
    }

    @Override
    public Point forward() {
        Point result = null;

        if (!isRowEnded()) {
            this.y++;

            result = new Point(this.x, this.y);

            if (isPointConstant(result)) {
                return forward();
            } else {
                return result;
            }
        } else if (!isColumnEnded()) {
            this.x++;
            this.y = 1;

            result = new Point(this.x, this.y);

            if (isPointConstant(result)) {
                return forward();
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    @Override
    public Point backward() {
        Point result = null;

        if (!isRowStarted()) {
            this.y--;

            result = new Point(this.x, this.y);

            if (isPointConstant(result)) {
                return backward();
            } else {
                return result;
            }
        } else if (!isColumnStarted()) {
            this.x--;
            this.y = this.dimension;

            result = new Point(this.x, this.y);

            if (isPointConstant(result)) {
                return backward();
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    @Override
    public BoardWalker start() {
        this.x = 1;
        this.y = 0;

        return this;
    }

    @Override
    public BoardWalker end() {
        this.x = this.dimension;
        this.y = this.dimension + 1;

        return this;
    }

    @Override
    public boolean canForward() {
        return this.x < this.dimension || this.y < this.dimension;
    }

    @Override
    public boolean canBackward() {
        return this.x > 1 || this.y > 1;
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

    public SimpleBoardWalker setConstantPoint(Point point) {
        this.constantPoints.add(point);

        return this;
    }
}
