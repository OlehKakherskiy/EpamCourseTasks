package shape;

/**
 * Created by User on 07.04.2016.
 */
public abstract class Shape implements Cloneable{

    public abstract double square();

    public abstract double perimeter();

    public abstract void move(double dx, double dy);

    @Override
    public String toString() {
        return " perimeter = " + perimeter() + " square = " + square();
    }
}
