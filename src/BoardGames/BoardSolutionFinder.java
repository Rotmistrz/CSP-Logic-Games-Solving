package BoardGames;

import BoardGames.Board;
import BoardGames.BoardWalker;
import BoardGames.SimpleBoardWalker;
import Coordinates.Point;

public class BoardSolutionFinder {
    public static final int NEUTRAL_NUMBER = 0;

    private Board board;
    private BoardWalker walker;

    private int dimension;

    private int[] domain;

    private int instructionsAmount = 0;

    public BoardSolutionFinder(Board board, BoardWalker walker) {
        this.board = board;
        this.walker = walker;

        this.dimension = this.board.getDimension();

        this.domain = new int[this.dimension];

        for (int i = 0; i < this.dimension; i++) {
            this.domain[i] = (i + 1);
        }
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean isAssignPossibility(int currentValue) {
        return currentValue <= this.dimension;
    }

    public int getFollowingValue(int currentValue) {
        return currentValue + 1;
    }

    public int getInstructionsAmount() {
        return this.instructionsAmount;
    }

    public boolean find() {
        //System.out.println();
        //this.board.present();

        BoardWalker walker = new SimpleBoardWalker(dimension);

        Point current;
        int x;
        int y;

        walker.end();

        while(walker.canBackward()) {
            current = walker.backward();

            x = current.getX();
            y = current.getY();

            if (this.board.get(x, y) == NEUTRAL_NUMBER) {
                for (int number = 1; number <= dimension; number++) {
                    if (this.board.isValueAllowed(x, y, number)) {
                        this.board.set(x, y, number);

                        instructionsAmount++;

                        if (find()) {
                            return true;
                        } else {
                            this.board.set(x, y, NEUTRAL_NUMBER);
                        }
                    }
                }

                return false;
            }
        }

        return true;
    }

    public boolean findForward() {
        //System.out.println();
        //this.board.present();

        BoardWalker walker = new SimpleBoardWalker(dimension);

        Point current;
        int x;
        int y;

        //walker.end();

        while(walker.canForward()) {
            current = walker.forward();
            x = current.getX();
            y = current.getY();

            if (this.board.get(x, y) == NEUTRAL_NUMBER) {
                for (int number = 1; number <= dimension; number++) {
                    if (this.board.isValueAllowed(x, y, number)) {
                        this.board.set(x, y, number);

                        instructionsAmount++;

                        if (find()) {
                            return true;
                        } else {
                            this.board.set(x, y, NEUTRAL_NUMBER);
                        }
                    }
                }

                return false;
            }
        }

        return true;
    }

    /**public boolean find(Point current) {
        System.out.println();
        this.board.present();

        if (this.board.isComplete() && this.board.check()) {
            return true;
        } else if (walker.canForward()) {
            Point following = walker.forward();

            int actualValue = this.board.get(current.getX(), current.getY());

            IntegerStream stream = new IntegerStream(1);
            int currentValue = stream.take();

            boolean result;

            while (isAssignPossibility(currentValue)) {
                if (actualValue == NEUTRAL_NUMBER) {
                    if (this.board.isValueAllowed(current.getX(), current.getY(), currentValue)) {
                        this.board.set(current.getX(), current.getY(), currentValue);

                        result = find(following);

                        if (result) {
                            return result;
                        } else {
                            this.board.set(current.getX(), current.getY(), NEUTRAL_NUMBER);
                        }
                    }

                    actualValue = this.board.get(current.getX(), current.getY());
                    currentValue = stream.take();
                }

            }

            return false;
        } else {
            return false;
        }
    }**/

    public boolean findSolution() {
        int currentValue;
        int actualValue;
        Point current = new Point(1, 1);
        int followingValue;

        Point temporary;

        this.board.makeCurrentBoardConstant();

        return find();

        /**while (walker.canForward()) {
            if (this.board.check()) {
                current = walker.forward();

                System.out.println("Naprzód! " + current);
            } else if (!this.board.check() && walker.canBackward()) {
                this.board.set(current.getX(), current.getY(), 0);

                current = walker.backward();

                System.out.println("Odwrót! " + current);
            } else {
                return false;
            }

            actualValue = this.board.get(current.getX(), current.getY());

            stream.reset();
            currentValue = stream.take();

            while (actualValue == NEUTRAL_NUMBER || (!this.board.check() && isAssignPossibility(currentValue))) {
                if (this.board.isPointConstant(current)) {
                    continue;
                }

                this.board.set(current.getX(), current.getY(), currentValue);

                actualValue = this.board.get(current.getX(), current.getY());

                currentValue = stream.take();

                //this.board.present();
                //System.out.println();
            }
        }

        return true;**/
    }
}
