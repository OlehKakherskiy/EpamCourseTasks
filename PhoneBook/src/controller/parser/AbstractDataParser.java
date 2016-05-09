package controller.parser;

import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractDataParser {

    protected Map<String, Pattern> regexp;

    protected String parsingRulesMessage;

    protected String errorMessage;

    public AbstractDataParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        this.regexp = regexp;
        this.parsingRulesMessage = parsingRulesMessage;
        this.errorMessage = errorMessage;
    }

    public boolean parseData(Contact contact, String data) {
        if (!applyCheckStrategy(data)) {
            return false;
        } else {
            completeModel(contact, data);
            return true;
        }
    }

    protected boolean applyCheckStrategy(String data) {
        for (Map.Entry<String, Pattern> regexpPair : regexp.entrySet())
            if (!regexpPair.getValue().matcher(data).lookingAt()) {
                return false;
            }
        return true;
    }

    protected abstract void completeModel(Contact contact, String data);

    public String getParsingRulesMessage() {
        return parsingRulesMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
