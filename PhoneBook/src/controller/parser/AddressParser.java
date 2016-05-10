package controller.parser;

import entity.Address;
import model.Contact;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AddressParser extends AbstractDataParser {


    public AddressParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        super(regexp, parsingRulesMessage, errorMessage);
    }

    @Override
    protected boolean applyCheckStrategy(String data) {
        Matcher matcher = regexp.get("countryPattern").matcher(data);
        String[] splitAddress = data.split(", ");
        return (regexp.get("countryPattern").matcher(splitAddress[0].trim()).matches() &&
                regexp.get("regionPattern").matcher(splitAddress[1].trim()).matches()) &&
                regexp.get("cityPattern").matcher(splitAddress[2].trim()).matches() &&
                regexp.get("streetPattern").matcher(splitAddress[3].trim()).matches() &&
                regexp.get("streetNumberPattern").matcher(splitAddress[4].trim()).matches() &&
                regexp.get("flatNumberPattern").matcher(splitAddress[5].trim()).matches() &&
                regexp.get("indexPattern").matcher(splitAddress[6].trim()).matches();
    }

    private boolean checkForNextRegexpMatch(Pattern pattern, Matcher matcher) {
        return matcher.usePattern(pattern).find();
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        String[] splitData = data.split(", ");
        for (int i = 0; i < splitData.length; i++) {
            splitData[i] = splitData[i].trim();
        }
        Address address = new Address(splitData[0], splitData[1], splitData[2],
                splitData[3], splitData[4], splitData[5], splitData[6]);
        contact.setAddress(address);
    }
}
