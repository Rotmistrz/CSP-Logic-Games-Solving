package Futoshiki;

import BoardGames.Board;
import BoardGames.BoardSolutionFinder;
import BoardGames.SimpleBoardWalker;

public class Futoshiki {
    public static void main(String[] args) {
        try {
            FutoshikiLoader loader = new FutoshikiLoader("E:\\Programowanie\\java\\projects\\LogicGames\\resources\\futoshiki\\test_futo_6_2.txt", 2);

            if (loader.load()) {
                int dimension = loader.getDimension();
                Board board = loader.getBoard();

                System.out.println(dimension);

                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        System.out.print(board.getByIndex(i, j) + " ");
                    }

                    System.out.println();
                }

                long startTime = System.nanoTime();

                BoardSolutionFinder finder = new BoardSolutionFinder(board, new SimpleBoardWalker(board.getDimension()));

                if (finder.findSolution()) {
                    System.out.println("Znaleziono rozwiązanie!");

                    Board solution = finder.getBoard();
                    solution.present();
                } else {
                    System.out.println("Nie znaleziono rozwiązania...");
                }

                long endTime = System.nanoTime();

                long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.

                System.out.println("\n" + duration + "ms, " + finder.getInstructionsAmount() + " instructions");

                /**int[][] expectedBoard = {
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

                int dim = expectedBoard.length;

                BoardGames.SimpleBoardWalker walker = new BoardGames.SimpleBoardWalker(dim);

                int boardLength = dim * dim;

                Point current;

                for (int i = 0; i < boardLength && walker.canForward(); i++) {
                    current = walker.forward();

                    System.out.println(current + " = " + (i + 1));

                    actualBoard[current.getX()][current.getY()] = (i + 1);
                }**/
            } else {
                System.out.println("Błąd");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}