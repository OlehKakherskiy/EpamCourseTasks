package task;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Controller {

    Scanner scanner = new Scanner(System.in);

    private View view;

    private Rectangle rectangle;

    public Controller(View view, Rectangle rectangle) {
        this.view = view;
        this.rectangle = rectangle;
    }

    public void processUser() {
        boolean repeatInputting = true;
        do {
            view.printMessage(View.INPUT_SIDE_LENGTH);
            repeatInputting = validateInputData();
        } while (!repeatInputting);

        view.printResult(rectangle.calculateDiagonal(), rectangle.calculatePerimeter());
    }


    /**
     * Performs validation of data, inputted by user through input stream.
     * If the data is not valid (empty string, consists of non-number chars, out of actual
     * range in model), exception text will be thrown to view for printing.
     *
     * @return <tt>true</tt>, if inputted data is valid. Otherwise, <tt>false</tt>
     */
    private boolean validateInputData() {
        boolean validateIsSuccessful = false;
        try {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            rectangle.initParameters(a, b);
            validateIsSuccessful = true;
        } catch (InputMismatchException e) { //if input data can't be parsed to integer value
            view.printMessage(View.EXCEPTION_MESSAGE, "inputted data not numbers.");
        } catch (NoSuchElementException e) { //look at explanation of Scanner.nextInt() method
            view.printMessage(View.EMPTY_STRING_EXCEPTION);
        } catch (IllegalArgumentException | ArithmeticException e) { //exceptions thrown from Rectangle object
            view.printMessage(View.EXCEPTION_MESSAGE, e.getMessage());
        } finally {
            scanner.nextLine(); //flush string with incorrect data if exception was thrown;
            return validateIsSuccessful;
        }
    }

}
