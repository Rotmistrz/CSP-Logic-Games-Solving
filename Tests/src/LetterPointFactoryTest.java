import Coordinates.LetterPointFactory;
import Coordinates.Point;
import junit.framework.TestCase;

public class LetterPointFactoryTest extends TestCase {
    public void testCreateFromString() {
        String probablyLetterPoint = "D4";

        LetterPointFactory factory = new LetterPointFactory();

        Point point;

        point = factory.createFromString(probablyLetterPoint);

        assertEquals(4, point.getX());
        assertEquals(4, point.getY());

        probablyLetterPoint = "A1";
        point = factory.createFromString(probablyLetterPoint);

        assertEquals(1, point.getX());
        assertEquals(1, point.getY());

        probablyLetterPoint = "E5";
        point = factory.createFromString(probablyLetterPoint);

        assertEquals(5, point.getX());
        assertEquals(5, point.getY());

        probablyLetterPoint = "F6";
        point = factory.createFromString(probablyLetterPoint);

        assertEquals(6, point.getX());
        assertEquals(6, point.getY());

        probablyLetterPoint = "C5";
        point = factory.createFromString(probablyLetterPoint);

        assertEquals(5, point.getX());
        assertEquals(3, point.getY());

    }
}
