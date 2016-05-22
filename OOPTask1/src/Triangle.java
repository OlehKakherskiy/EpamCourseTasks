/**
 * Class represents triangle's figure, that consists from 3 interconnected points,
 * has own perimeter and square. It also can calculate the height from each point to
 * the opposite side.
 * <p>
 * All sides can be calculated using {@link #calculateSide(Point, Point)} method.
 * Caches perimeter, square and all sides values.
 * If one point is changed perimeter, square values are resets and sides, consisted of this
 * point, are recalculated.
 * </p>
 * <p>
 * Throws {@link FigureConfigurationException} if side is negative or perimeter value
 * is bigger than {@link Integer#MAX_VALUE}
 * </p>
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Triangle extends Figure {

    /**
     * Perimeter of triangle
     */
    private double perimeter;

    /**
     * Square of triangle
     */
    private double square;

    /**
     * Length between {@link #points}[1] and {@link #points}[2]
     */
    private double sideA;

    /**
     * Length between {@link #points}[0] and {@link #points}[2]
     */
    private double sideB;

    /**
     * Length between {@link #points}[0] and {@link #points}[1]
     */
    private double sideC;

    /**
     * Creates new triangle with 3 coordinates. Also calculates values of all sides of this
     * triangle using coordinates.
     *
     * @param a {@link #points}[0]
     * @param b {@link #points}[1]
     * @param c {@link #points}[2]
     * @throws FigureConfigurationException if value of one side is negative
     */
    public Triangle(Point a, Point b, Point c) throws FigureConfigurationException {
        super(a, b, c);
        sideA = calculateSide(b, c);
        sideB = calculateSide(a, c);
        sideC = calculateSide(a, b);
        perimeter = -1;
        square = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double perimeter() throws FigureConfigurationException {
        if (sideA + sideB + sideC > Integer.MAX_VALUE)
            throw new IllegalArgumentException(Figure.incompatibleSidesException);
        perimeter = perimeter != -1 ? perimeter : sideA + sideB + sideC;
        return perimeter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double square() throws FigureConfigurationException {
        double p = perimeter() / 2;
        square = Math.sqrt(p) * Math.sqrt(p - sideA) * Math.sqrt(p - sideB) * Math.sqrt(p - sideC);
        return square;
    }

    /**
     * Calculates the height between point, which position is the parameter,
     * and opposite side of the triangle. The length is calculated using formulae:
     * <code>h = 2S/a</code>, where h - height, S - triangle's square, a - side length
     *
     * @param index point's index in {@link #points} array
     * @return height length between specific point and opposite side to this point.
     * @throws FigureConfigurationException if perimeter's value is bigger than {@link Integer#MAX_VALUE}
     */
    public double calculateHeight(int index) throws FigureConfigurationException {
        if (index > points.length)
            return -1;
        double side = -1;
        switch (index) {
            case 0: {
                side = sideA;
                break;
            }
            case 1: {
                side = sideB;
                break;
            }
            case 2: {
                side = sideC;
                break;
            }
        }
        return 2 * square() / side;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void pointWasUpdated(int index) throws FigureConfigurationException {
        perimeter = -1;
        square = -1;
        switch (index) {
            case 0: { //point a
                sideB = calculateSide(points[0], points[2]);
                sideC = calculateSide(points[0], points[1]);
                break;
            }
            case 1: { //point b
                sideA = calculateSide(points[1], points[2]);
                sideC = calculateSide(points[0], points[1]);
                break;
            }
            case 2: { //point c
                sideB = calculateSide(points[0], points[2]);
                sideA = calculateSide(points[1], points[2]);
            }
        }
    }

    /**
     * calculate the length of side between two points using formulae :
     * <code>((x1-x2)^2 + (y1-y2)^2)^(1/2)</code>
     *
     * @param p1 first point
     * @param p2 second point
     * @return length between points
     * @throws FigureConfigurationException if length is negative
     */
    private double calculateSide(Point p1, Point p2) throws FigureConfigurationException {
        double result = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2.0) + Math.pow(p1.getY() - p2.getY(), 2.0));
        if (result < 0) {
            throw new IllegalArgumentException(Figure.incompatibleSidesException);
        }
        return result;
    }
}
