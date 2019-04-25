package Skyscrapper;

import BoardGames.Board;
import BoardGames.BoardChecker;
import BoardGames.BoardConstraint;
import Skyscrapper.Constraints.EastSkyscrapperConstraint;
import Skyscrapper.Constraints.NorthSkyscrapperConstraint;
import Skyscrapper.Constraints.SouthSkyscrapperConstraint;
import Skyscrapper.Constraints.WestSkyscrapperConstraint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SkyscrapperLoader {
    public static final String NORTH_SYMBOL = "G";
    public static final String SOUTH_SYMBOL = "D";
    public static final String WEST_SYMBOL = "L";
    public static final String EAST_SYMBOL = "P";

    private String path;
    private int metaDataAmount;
    private int dimension;
    private LinkedList<BoardConstraint> constraints;

    private BufferedReader reader;

    private String problemName;

    private String[] metaData;
    private int[][] boardArray;
    private Board board;

    public SkyscrapperLoader(String path, int metaDataAmount) throws FileNotFoundException {
        this.path = path;
        this.metaDataAmount = metaDataAmount;

        this.reader = new BufferedReader(new FileReader(this.path));

        metaData = new String[metaDataAmount];

        constraints = new LinkedList<BoardConstraint>();
    }

    public int getDimension() {
        return this.dimension;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean load() {
        String line;

        // metadata
        try {
            for (int i = 0; i < this.metaDataAmount; i++) {
                this.metaData[i] = this.reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        String[] lineParts;
        String directionSymbol;

        this.dimension = Integer.parseInt(this.metaData[0]);

        this.board = new Board(this.dimension, new BoardChecker());

        int current;

        this.board.reset();

        // constraints
        try {
            for (int i = 0; i < 4; i++) {
                line = this.reader.readLine();
                lineParts = line.split(";");

                directionSymbol = lineParts[0];

                for (int j = 1; j < lineParts.length; j++) {
                    current = Integer.parseInt(lineParts[j]);

                    if (current != 0) {
                        System.out.println(directionSymbol + " [" + j + "]: " + current);

                        BoardConstraint constraint = createConstraint(directionSymbol, j, current);
                        this.board.setConstraint(constraint);
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }

    public BoardConstraint createConstraint(String symbol, int line, int visibleAmount) {
        if (symbol.equals(SOUTH_SYMBOL)) {
            return new SouthSkyscrapperConstraint(line, visibleAmount);
        } else if (symbol.equals(WEST_SYMBOL)) {
            return new WestSkyscrapperConstraint(line, visibleAmount);
        } else if (symbol.equals(EAST_SYMBOL)) {
            return new EastSkyscrapperConstraint(line, visibleAmount);
        } else {
            return new NorthSkyscrapperConstraint(line, visibleAmount);
        }
    }
}