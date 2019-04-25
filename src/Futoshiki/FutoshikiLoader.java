package Futoshiki;

import BoardGames.Board;
import BoardGames.BoardChecker;
import BoardGames.BoardConstraint;
import Coordinates.LetterPointFactory;
import Coordinates.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class FutoshikiLoader {
    private String path;
    private int metaDataAmount;
    private int dimension;
    private LinkedList<BoardConstraint> constraints;

    private BufferedReader reader;

    private String problemName;

    private String[] metaData;
    private int[][] boardArray;
    private Board board;

    public FutoshikiLoader(String path, int metaDataAmount) throws FileNotFoundException {
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
            return false;
        }

        String[] lineParts;

        this.dimension = Integer.parseInt(this.metaData[0]);

        this.board = new Board(this.dimension, new BoardChecker());

        int current;

        try {
            for (int i = 0; i < this.dimension; i++) {
                line = this.reader.readLine();
                lineParts = line.split(";");

                for (int j = 0; j < lineParts.length; j++) {
                    current = Integer.parseInt(lineParts[j]);

                    this.board.setByIndex(i, j, current);
                }
            }
        } catch (Exception e) {
            return false;
        }

        // constraints
        try {
            this.reader.readLine();

            int index;

            String letterPointStr;

            LetterPointFactory factory = new LetterPointFactory();

            while((line = this.reader.readLine()) != null) {
                lineParts = line.split(";");

                Point letterPoint = factory.createFromString(lineParts[0]);
                Point letterPointGreater = factory.createFromString(lineParts[1]);

                InequalityConstraint constraint = new InequalityConstraint(letterPoint, letterPointGreater);

                this.board.setConstraint(constraint);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }


        return true;
    }
}
