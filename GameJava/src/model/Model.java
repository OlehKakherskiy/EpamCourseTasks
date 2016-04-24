package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class represents Model component of MVC. Encapsulates data and protects its consistency.
 * Services data access methods and some business logic methods ({@link #rand(), {@link #checkIfEquals(int)}}
 * for controller. This methods helps model to be entire in itself, therefore model can be used repeatedly.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Model {

    /**
     * max value of possible generated number.
     */
    public static int MAX_RANDOM = 100;

    /**
     * min value of possible generated number.
     */
    public static int MIN_RANDOM = 0;

    /**
     * used to generate int values
     */
    private static Random numberGenerator = new Random();

    /**
     * number generated by model and must be guessed by user
     */
    private int rightNumber;

    /**
     * history of changing max boundary
     */
    private ArrayList<Integer> maxValues;

    /**
     * history of changing mix boundary
     */
    private ArrayList<Integer> minValues;

    /**
     * history of values inputted by user
     */
    private ArrayList<Integer> inputtedNumbers;


    public Model() {
        maxValues = new ArrayList<>();
        minValues = new ArrayList<>();
        inputtedNumbers = new ArrayList<>();
        rightNumber = rand();
    }

    /**
     * generates random value in range [{@link Model#MIN_RANDOM}, {@link Model#MAX_RANDOM}]
     *
     * @return generated value
     */
    public int rand() {
        return rand(MIN_RANDOM, MAX_RANDOM);
    }

    /**
     * Generates new random int in range [min,max]. If max > {@link Model#MAX_RANDOM}
     * then will truncated MAX_RANDOM. If min < {@link Model#MIN_RANDOM} then whill
     * be upped to MIN_RANDOM
     *
     * @param min start number of range, in which random value will be generated
     * @param max end number of range, in which random value will be generated
     * @return random value in range [min, max]
     */
    public int rand(int min, int max) {
        max = max <= MAX_RANDOM ? max : MAX_RANDOM;
        min = min >= MIN_RANDOM ? min : MIN_RANDOM;
        maxValues.add(max);
        minValues.add(min);
        return numberGenerator.nextInt(max - min + 1) + min;
    }

    /**
     * checks if param is equal to right number. if true, returns true value and game is finished
     * - user wins. Otherwise changes one of boundaries of range, so that user will guess a number
     * from narrower range
     *
     * @param checkNumber number inputted by user
     * @return <tt>true</tt> if user wins, otherwise - <tt>false</tt>
     */
    public boolean checkIfEquals(int checkNumber) {
        inputtedNumbers.add(checkNumber);
        boolean isEqual = checkNumber == rightNumber;
        if (!isEqual) {
            if (checkNumber < rightNumber)
                addRangeToStatistics(checkNumber, maxValues.get(maxValues.size() - 1));
            else addRangeToStatistics(minValues.get(minValues.size() - 1), checkNumber);
        }
        return isEqual;
    }

    /**
     * add params to lists of min/max values. From now this values are actual
     *
     * @param min
     * @param max
     */
    private void addRangeToStatistics(int min, int max) {
        minValues.add(min);
        maxValues.add(max);
    }

    /**
     * checks if number, inputted by user, is in range of actual range
     * (placed between {@link #minValues},{@link #maxValues}). This method must be used as a part
     * of input data validation for protection the model consistency.
     *
     * @param min            actual min value
     * @param max            actual max value
     * @param inputtedNumber number inputted by user
     * @return <tt>true</tt>, if number is between min and max. Otherwise <tt>false</tt>
     */
    public boolean validateNumberIsInRange(int min, int max, int inputtedNumber) {
        return inputtedNumber >= min && inputtedNumber <= max;
    }

    /**
     * returns the last actual min value of range, where {@link #rightNumber} is placed
     *
     * @return last actual min value
     */
    public int getActualMin() {
        return minValues.get(minValues.size() - 1);
    }

    /**
     * returns the last actual max value of range, where {@link #rightNumber} is placed
     *
     * @return last actual max value
     */
    public int getActualMax() {
        return maxValues.get(maxValues.size() - 1);
    }

    /**
     * returns the number, which is generated and which must be guessed by user
     *
     * @return generated number
     */
    public int getRightNumber() {
        return rightNumber;
    }

    /**
     * History of range's max value changing according to game rules
     *
     * @return all max values of this game
     */
    public ArrayList<Integer> getMaxValues() {
        return maxValues;
    }

    /**
     * History of range's min value changing according to game rules
     *
     * @return all min values of this game
     */
    public ArrayList<Integer> getMinValues() {
        return minValues;
    }

    /**
     * History of values inputted by user
     *
     * @return all values inputted by user
     */
    public ArrayList<Integer> getInputtedNumbers() {
        return inputtedNumbers;
    }
}
