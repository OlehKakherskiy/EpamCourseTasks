package task;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class View {


    public static final String INPUT_SIDE_LENGTH = "Input the length of 2 different sides. Input number and press Enter";

    public static final String EMPTY_STRING_EXCEPTION = "Empty string. You should input number symbols. " +
            "Repeat the inputting, please";

    public static final String EXCEPTION_MESSAGE = "Exception. %s Repeat the inputting, please";

    public static final String RESULT_OUTPUT = "Result. Diagonal = %f. Perimeter = %f";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(String message, String additionalMessage) {
        System.out.println(String.format(message, additionalMessage));
    }

    public void printResult(double diagonal, double perimeter) {
        System.out.println(String.format(RESULT_OUTPUT, diagonal, perimeter));
    }
}
