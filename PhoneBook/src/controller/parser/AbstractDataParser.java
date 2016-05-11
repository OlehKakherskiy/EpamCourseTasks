package controller.parser;

import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class is used to parse inputted data and to insert it to initialize a
 * special field of {@link Contact} object. Every object has special rules for
 * inputted data representation, special error message with error explanations
 * and regexp patterns map. This object can have more than one pattern and apply these
 * patterns in order, which algorithm is in {@link #applyCheckStrategy(String)}. If
 * this method returns true, then {@link Contact} object may be completed by
 * String data representation.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see Contact
 */
public abstract class AbstractDataParser {

    /**
     * regular expressions, used in data validation
     */
    protected Map<String, Pattern> regexp;

    /**
     * String representing format of inputted data
     */
    protected String parsingRulesMessage;

    /**
     * String representing exception of data validation.
     */
    protected String errorMessage;

    /**
     * Constructor
     *
     * @param regexp              regexp patterns in "key-value" format
     * @param parsingRulesMessage format inputted data should be represented in
     * @param errorMessage        message describing validation exception
     */
    public AbstractDataParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        this.regexp = regexp;
        this.parsingRulesMessage = parsingRulesMessage;
        this.errorMessage = errorMessage;
    }

    /**
     * Method is used for model completion by valid inputted data. Firstly, check data
     * and then complete model by this data. Method mustn't be override, so has modifier
     * final. Must be always called, when {@link Contact} object might be completed by
     * inputted data.
     *
     * @param contact entity, that should be completed by valid inputted data
     * @param data    string representation of some data, that should be added to model
     * @return true, if data is valid and model is completed, otherwise - false.
     */
    public final boolean parseData(Contact contact, String data) {
        if (!applyCheckStrategy(data)) {
            return false;
        } else {
            completeModel(contact, data);
            return true;
        }
    }

    /**
     * Method represents algorithm of parsing inputted data using regexps.
     * Result of method - whether data is valid or invalid.
     *
     * @param data data, needed to be checked
     * @return true, if data is valid, otherwise - false
     */
    protected boolean applyCheckStrategy(String data) {
        for (Map.Entry<String, Pattern> regexpPair : regexp.entrySet())
            if (!regexpPair.getValue().matcher(data).lookingAt()) {
                return false;
            }
        return true;
    }

    /**
     * Method is used to complete {@link Contact} object with valid inputted data.
     * Must be implemented by descendants.
     *
     * @param contact model, that should be completed by data, that has string
     *                representation
     * @param data string representation of different data
     */
    protected abstract void completeModel(Contact contact, String data);

    public String getParsingRulesMessage() {
        return parsingRulesMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
