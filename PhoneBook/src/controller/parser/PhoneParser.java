package controller.parser;

import entity.PhoneNumber;
import entity.PhoneType;
import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class PhoneParser extends AbstractDataParser {


    public PhoneParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        super(regexp, parsingRulesMessage, errorMessage);
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        String[] phonesStrings = data.split(";");
        for (String phoneData : phonesStrings) {
            contact.addPhoneNumber(parseOnePhoneNumber(phoneData));
        }
    }

    private PhoneNumber parseOnePhoneNumber(String phoneNumberData) {
        String[] splitData = phoneNumberData.split(" ");
        return new PhoneNumber(splitData[1], PhoneType.valueOf(splitData[0]));
    }
}
