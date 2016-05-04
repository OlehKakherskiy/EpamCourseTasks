package controller;

import model.Model;
import view.View;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class represents Controller component of MVC. It provides binding between
 * {@link Model} and {@link View} objects, so that user change model using view through controller
 * and all changes in model are represents to user in view through controller.
 * <p>
 * Encapsulate business-logic of game. Takes data inputted by user, validate it and change model.
 * Then represents changes using view object.
 * <p>
 * {@see View}
 * {@see Model}
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Controller {

    /**
     * String template of game iteration. Is used by {@link String#format(String, Object...)}.
     * First position - min value of range, second - max value, third - user inputted value,
     * fourth - result of game iteration (if game was finished).
     */
    private static String outputTemplate = "range: [%d, %d], inputtedNumber is %d, is rightNumber: %b. \n";

    /**
     * Model object, that encapsulates all data needed for playing
     */
    private Model model;

    /**
     * View object, used for
     */
    private View view;


    /**
     * Number that is inputted from inputStream on every iteration
     */
    private int inputtedNumber;

    /**
     * initialise controller with View, Model object and stream
     * which data is inputting from.
     *
     * @param model
     * @param view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * business logic of a game. Has a cycle, that will be performed
     * until user guess the value, generated in model. After that,
     * prepare statistical data and outputs it through the view object.
     */
    public void processUser() {
        boolean finishGame = false;
        //game business logic
        model.rand();
        do {
            int currentMin = model.getActualMin();
            int currentMax = model.getActualMax();

            view.printMessage(View.INPUT_NEXT_NUMBER, currentMin, currentMax);

            if (validateInputData(currentMin, currentMax)) {
                finishGame = model.checkIfEquals(inputtedNumber);
                view.printTheResultOfGuessing(finishGame);
            }
        } while (!finishGame);

        //print statistical data
        view.printMessage(View.PRINT_STATISTICAL_DATA, formatStatisticData());
    }

    /**
     * Performs validation of data, inputted by user through input stream.
     * If the data is not valid (empty string, consists of non-number chars, out of actual
     * range in model), exception text will be thrown to view for printing.
     *
     * @param min start number of range
     * @param max end number of range
     * @return <tt>true</tt>, if inputted data is valid. Otherwise, <tt>false</tt>
     */
    private boolean validateInputData(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        boolean validateIsSuccessful = false;
        try {
            inputtedNumber = scanner.nextInt();
            //check whether model will validate this number. If not, number is out of current iteration range
            validateIsSuccessful = model.validateNumberIsInRange(min, max, inputtedNumber);
            if (!validateIsSuccessful) {
                view.printMessage(View.NUMBER_OUT_OF_RANGE_EXCEPTION, min, max);
            }
        } catch (InputMismatchException e) { //if input data can't be parsed to integer value
            view.printMessage(View.NOT_A_NUMBER_EXCEPTION);
        } catch (NoSuchElementException e) { //look at explanation of Scanner.nextInt() method
            view.printMessage(View.EMPTY_STRING_EXCEPTION);
        } finally {
            scanner.nextLine(); //flush string with incorrect data if exception was thrown;
            return validateIsSuccessful;
        }
    }

    /**
     * Formats statistical data of this game. Prints right number and all user attempts to guess the
     * right number.
     *
     * @return statistical data of this game.
     */
    private String formatStatisticData() {
        StringBuilder resultBuilder = new StringBuilder(String.format("Right number is %d. \n", model.getRightNumber()));

        //format all false steps (last step is always true)
        int iterationsCount = model.getInputtedNumbers().size();
        for (int i = 0; i < iterationsCount - 1; i++) {
            resultBuilder.append(formatIterationStatistic(i, false));
        }

        //format last step
        resultBuilder.append(formatIterationStatistic(iterationsCount - 1, true));
        return resultBuilder.toString();
    }

    /**
     * Formats string representation of user guess with number <tt>iteration</tt>.
     * The representation consists of min/max values of inputted iteration number, number
     * inputted by user and attempt result.
     *
     * @param iteration       user attempt number
     * @param iterationResult true if user guessed the number. Otherwise, false
     * @return string representation of user attempt iteration
     */
    private String formatIterationStatistic(int iteration, boolean iterationResult) {
        return String.format(outputTemplate, model.getMinValues().get(iteration), model.getMaxValues().get(iteration),
                model.getInputtedNumbers().get(iteration), iterationResult);
    }
}
