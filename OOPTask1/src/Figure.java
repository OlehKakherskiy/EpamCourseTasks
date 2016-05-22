import java.util.Arrays;

/**
 * Class represents a figure. Each geometrical figure consists of several points, has a perimeter and
 * a square. So that every descendant should implement methods of calculating these two parameters.
 * <p>
 * Figure object aggregates an array of {@link Point}, therefore two objects are equal, if they points are equal
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see Point
 */
public abstract class Figure {

    /**
     * string representation of exception thrown when or figure side < 0, or all sides > {@link Integer#MAX_VALUE}
     */
    protected static final String incompatibleSidesException =
            "Incompatible coordinates of triangle - too large sides. Can't perform perimeter calculations";

    /**
     * string representation of exception thrown if line can be built through all points - figure can't be built
     */
    protected static final String similarLinePointCoordinatesException =
            "All points are on similar line - can't build figure";

    /**
     * {@link Point} objects, which figure is consisted from
     */
    protected Point[] points;

    /**
     * Creates a figure from these points. Checks whether figure can be build using these points
     *
     * @param points figure points
     * @throws FigureConfigurationException {@link #figureConditionCheck(Point...)} returns false
     */
    public Figure(Point... points) throws FigureConfigurationException {
        if (!figureConditionCheck(points)) {
            throw new IllegalArgumentException(similarLinePointCoordinatesException);
        }
        this.points = points;
    }

    /**
     * calculates perimeter of figure.
     *
     * @return figure perimeter
     * @throws FigureConfigurationException if side < 0 because of integer overflow or if
     *                                      sum of all sides > {@link Integer#MAX_VALUE}
     */
    public abstract double perimeter() throws FigureConfigurationException;

    /**
     * calculates figure square
     *
     * @return figure square
     * @throws FigureConfigurationException check {@link #perimeter()} documentation
     */
    public abstract double square() throws FigureConfigurationException;

    /**
     * called after one of points was updated, so all cached information of figure should
     * be reset or recalculated.
     *
     * @param index point index, that has been changed
     * @throws FigureConfigurationException throws if some figure parameters can't be
     *                                      recalculated because of changed point
     */
    protected abstract void pointWasUpdated(int index) throws FigureConfigurationException;

    /**
     * Except standard checks check whether figures' points are equal.
     *
     * @param o {@link Figure} object
     * @return true if objects are equal, otherwise - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Figure)) return false;

        Figure figure = (Figure) o;
        for (Point p : this.points) {
            for (Point point : figure.points) {
                if (p.equals(point))
                    break;
                else return false;
            }
        }
        return true;
    }

    /**
     * @return figure object hashcode
     * @see Arrays#hashCode(Object[])
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    /**
     * returns clone of point from {@link #points} with this index. Mustn't be redefined,
     * so final modifier is added.
     *
     * @param index point's position in {@link #points}. if > points.length - return false.
     * @return point clone from special position in {@link #points}
     * @throws CloneNotSupportedException ignore
     */
    public final Point getPoint(int index) throws CloneNotSupportedException {
        return index > points.length ? null : points[index].clone();
    }

    /**
     * set {@link Point} object to the specific position. If position-parameter > points.length then
     * method do nothing. Also after completed setting the point {@link #pointWasUpdated(int)} is called.
     * If exception was thrown by {@link #pointWasUpdated(int)} that new point is replaced by old one.
     *
     * @param index specific position of point object that should be set to.
     * @param p     point object that should be added to specific position if figure.
     * @throws FigureConfigurationException look at {@link #pointWasUpdated(int)}
     */
    public final void setPoint(int index, Point p) throws FigureConfigurationException {
        Point prevPoint = null;
        try {
            if (index < points.length) {
                prevPoint = points[index];
                points[index] = p;
                pointWasUpdated(index);
            }
        } catch (FigureConfigurationException e) {
            points[index] = prevPoint;
            throw new FigureConfigurationException(e.getMessage(), e.getCause());
        }
    }

    /**
     * checks whether a line can be built through all points. Figure can't be built if all points
     * are positioned on the straight line
     *
     * @param points points figure should be built from
     * @return true if figure can be built using this points. Otherwise - false
     */
    private boolean figureConditionCheck(Point... points) {
        boolean flag = false;
        check:
        for (Point p : points) {
            for (Point p1 : points) {
                if (p.getX() != p1.getX() && p.getY() != p1.getY()) {
                    flag = true;
                    break check;
                }
            }
        }
        return flag;
    }
}
