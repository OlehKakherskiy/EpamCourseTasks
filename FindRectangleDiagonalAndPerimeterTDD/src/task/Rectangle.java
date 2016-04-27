package task;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Rectangle {

    private static final String OVERFLOW_EXCEPTION = "a + b > max int value. Calculations are incorrect.";

    private static final String INCORRECT_ARGUMENT_EXCEPTION = "%s argument must be positive";

    private int a;

    private int b;

    public Rectangle() {

    }

    public Rectangle(int a, int b) {
        initParameters(a, b);
    }

    public int calculatePerimeter() {
        return 2 * (a + b);
    }

    public void initParameters(int a, int b) {
        validateInputParameters(a, b);

        this.a = a;
        this.b = b;
    }

    public double calculateDiagonal() {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    private void validateInputParameters(int a, int b) {
        checkForIncorrectValues(a, b);
        checkForOverflow(a, b);
    }

    private void checkForOverflow(int a, int b) {
        if (Integer.MAX_VALUE - a - b < 0) {
            throw new ArithmeticException(OVERFLOW_EXCEPTION);
        }
    }

    private void checkForIncorrectValues(int a, int b) {
        if (a <= 0) {
            throw new IllegalArgumentException(String.format(INCORRECT_ARGUMENT_EXCEPTION, "First"));
        }
        if (b <= 0) {
            throw new IllegalArgumentException(String.format(INCORRECT_ARGUMENT_EXCEPTION, "Second"));
        }
    }
}