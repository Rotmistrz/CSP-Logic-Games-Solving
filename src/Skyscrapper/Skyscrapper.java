package Skyscrapper;

import BoardGames.Board;
import BoardGames.SimpleBoardWalker;
import BoardGames.BoardSolutionFinder;

public class Skyscrapper {
    public static void main(String[] args) {
        try {
            SkyscrapperLoader loader = new SkyscrapperLoader("E:\\Programowanie\\java\\projects\\LogicGames\\resources\\skyscrapper\\test_sky_4_2.txt", 1);

            if (loader.load()) {
                int dimension = loader.getDimension();
                Board board = loader.getBoard();

                //System.out.println(dimension);

                /**for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        System.out.print(board.getByIndex(i, j) + " ");
                    }

                    System.out.println();
                }**/

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
            } else {
                System.out.println("Błąd");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
