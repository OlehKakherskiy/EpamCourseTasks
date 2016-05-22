/**
 * Represents point in Decart's coordinates. Each point consists of (x,y).
 * Can be cloned.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Point implements Cloneable {

    /**
     * abscissa
     */
    private int x;

    /**
     * ordinates
     */
    private int y;

    /**
     * Creates new point
     * @param x abscissa
     * @param y ordinates
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * clones this object.
     * @return new {@link Point} with similar coordinates
     * @throws CloneNotSupportedException ignore
     */
    @Override
    public Point clone() throws CloneNotSupportedException {
        return new Point(this.x, this.y);
    }

    /**
     * Except standard checks check whether coordinates are similar
     *
     * @param o object that is checked for equality with current one
     * @return true if objects are similar, otherwise - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;

    }

    /**
     * returns hashcode of this object. Hashcode consists from x and y values and constant
     *
     * @return object's hashcode
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + "}";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
