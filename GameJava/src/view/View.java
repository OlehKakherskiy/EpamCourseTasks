package view;

/**
 * Class represents View part of MVC pattern. It has methods and data,
 * need to communicate with user. Used by controller to output response for user input
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class View {

    public static String INPUT_NEXT_NUMBER = "Input next number from the range [%d, %d]";

    public static String NOT_A_NUMBER_EXCEPTION = "You should input number symbols. Repeat the action, please";

    public static String NUMBER_OUT_OF_RANGE_EXCEPTION = "You should input a number from the range [%d, %d]. " +
            "Repeat the action, please";

    public static String EMPTY_STRING_EXCEPTION = "Empty string. You should input number symbols. " +
            "Repeat the action, please";

    public static String EQUAL_NUMBERS = "Yes. You guessed my number";

    public static String NOT_EQUALS = "No. You didn't guess my number. Try again";

    public static String PRINT_STATISTICAL_DATA = "Statistical data. %s";


    /**
     * Prints the message to console.
     *
     * @param message message that should be output to console
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message with two int numbers.
     *
     * @param message string that should be output to console. Must be prepared for
     *                {@link String#format} method.
     * @param min     minimal value of range, where prepared number value is in.
     * @param max     maximum value of range, where prepared number value is in.
     */
    public void printMessage(String message, int min, int max) {
        System.out.println(String.format(message, min, max));
    }

    /**
     * Prints the message with additional string in special position.
     *
     * @param message     string that should be output to console. Must be prepared for
     *                    {@link String#format} method.
     * @param messagePart any string
     */
    public void printMessage(String message, String messagePart) {
        System.out.println(String.format(message, messagePart));
    }

    /**
     * Prints the result of game iteration according to input parameter.
     *
     * @param guessNumberIsRight if <tt>true</tt>, prints congratulations for user.
     *                           Otherwise, prints that user guess is wrong
     */
    public void printTheResultOfGuessing(boolean guessNumberIsRight) {
        if (guessNumberIsRight) {
            printMessage(EQUAL_NUMBERS);
        } else {
            printMessage(NOT_EQUALS);
        }
    }
}
