package controller.parser;

import entity.Address;
import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AddressParser extends AbstractDataParser {


    public AddressParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        super(regexp, parsingRulesMessage, errorMessage);
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        String[] splitData = data.split(",");
        for (int i = 0; i < splitData.length; i++) {
            splitData[i] = splitData[i].trim();
        }
        Address address = new Address(splitData[0], splitData[1], splitData[2],
                splitData[3], splitData[4], splitData[5], splitData[6]);
        contact.setAddress(address);
    }
}
