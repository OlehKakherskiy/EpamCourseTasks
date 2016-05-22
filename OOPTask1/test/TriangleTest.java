import org.junit.Assert;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TriangleTest {

    @Test
    public void testPerimeter() throws Exception {
        Figure t = new Triangle(new Point(1, 2), new Point(2, 6), new Point(0, 0));
        Assert.assertEquals(12.683728923454211, t.perimeter(), 0.e-20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPerimeterException() throws Exception {
        Figure t = new Triangle(new Point(1, 2), new Point(2, 6), new Point(0, 0));
        t.perimeter();
        t.setPoint(1, new Point(Integer.MAX_VALUE / 2, 1));
        t.setPoint(0, new Point(Integer.MAX_VALUE / 2 + 1000000, 50));
        t.perimeter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPerimeterException2() throws Exception {
        new Triangle(new Point(Integer.MAX_VALUE, 10), new Point(-1005001, 15), new Point(100000000, 45)).perimeter();

    }


    @Test
    public void testSquare() throws Exception {
        Figure t = new Triangle(new Point(1, 2), new Point(2, 6), new Point(0, 0));
        Assert.assertEquals(1.0000000000000224, t.square(), 0.e-20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() throws Exception {
        new Triangle(new Point(1, 2), new Point(5, 2), new Point(7, 2));
    }

}