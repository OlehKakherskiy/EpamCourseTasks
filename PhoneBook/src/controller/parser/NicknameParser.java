package controller.parser;

import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class NicknameParser extends AbstractDataParser {


    public NicknameParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        super(regexp, parsingRulesMessage, errorMessage);
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        contact.setNickName(data);
    }
}
