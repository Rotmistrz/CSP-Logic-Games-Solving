package Coordinates;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public Point setX(int x) {
        this.x = x;

        return this;
    }

    public int getY() {
        return this.y;
    }

    public Point setY(int y) {
        this.y = y;

        return this;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Point another) {
        return getX() == another.getX() && getY() == another.getY();
    }
}