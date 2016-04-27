package task;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class RectangleTest {

    public static Rectangle rectangle = new Rectangle();

    public static double ACCURACY = 1e-20;

    @Test(expected = IllegalArgumentException.class)
    public void testInitZeroNumbers() throws Exception {
        rectangle.initParameters(0, 5);
    }

    public void testInit() throws Exception {
        rectangle.initParameters(2, 3);
        Assert.assertTrue("First in parameter is not initialized in Rectangle object",
                (2 == rectangle.getA()) || (2 == rectangle.getB()));
        Assert.assertTrue("Second in parameter is not initialized in Rectangle object",
                (3 == rectangle.getA()) || (3 == rectangle.getB()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitNegativeNumbers() throws Exception {
        rectangle.initParameters(5, -1);
    }

    @Test(expected = ArithmeticException.class)
    public void testInitOverflowArgumentsMaxValue() throws Exception {
        rectangle.initParameters(Integer.MAX_VALUE, 2);
    }

    @Test(expected = ArithmeticException.class)
    public void testInitOverflowArgumentsBigValues() throws Exception {
        rectangle.initParameters(Integer.MAX_VALUE / 2 + 1, Integer.MAX_VALUE / 2 + 40);
    }

    @Test
    public void testCalculateDiagonal() throws Exception {
        rectangle.initParameters(3, 4);
        assertEquals(5.0, rectangle.calculateDiagonal(), ACCURACY);
    }

    public void testCalculateDiagonalDoubleResult() throws Exception {
        rectangle.initParameters(6, 7);
        assertEquals(9.2195444572928873100022742817628, rectangle.calculateDiagonal(), ACCURACY);
    }

    @Test
    public void testCalculatePerimeter() throws Exception {
        rectangle.initParameters(2, 3);
        assertEquals(10, rectangle.calculatePerimeter());
    }
}