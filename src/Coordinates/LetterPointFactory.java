package Coordinates;

public class LetterPointFactory extends PointFactory {
    @Override
    public Point createFromString(String str) {
        String letter = str.substring(0, 1);
        String number = str.substring(1);

        int x = Integer.parseInt(number);
        int y = getIndexOfLetter(letter);

        return new Point(x, y);
    }

    public int getIndexOfLetter(String letter) {
        if (letter.equals("A")) {
            return 1;
        } else if (letter.equals("B")) {
            return 2;
        } else if (letter.equals("C")) {
            return 3;
        } else if (letter.equals("D")) {
            return 4;
        } else if (letter.equals("E")) {
            return 5;
        } else if (letter.equals("F")) {
            return 6;
        } else if (letter.equals("G")) {
            return 7;
        } else if (letter.equals("H")) {
            return 8;
        } else {
            return -1;
        }
    }
}
