package controller.parser;

import entity.PhoneNumber;
import entity.PhoneType;
import model.Contact;

import java.util.Arrays;
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
    protected boolean applyCheckStrategy(String data) {
        String[] numbers = data.split(";|; ");
        System.out.println("numbers = " + Arrays.toString(numbers));
        System.out.println(regexp.get("phoneTypePattern"));
        System.out.println(regexp.get("phoneNumberPattern"));
        System.out.println(regexp.get("phoneNumberPattern").matcher(data).find());
        System.out.println(regexp.get("phoneTypePattern").matcher(data).find());
        for (String number : numbers) {
            String[] numberParts = number.trim().split(" ");
            if (numberParts.length < 2)
                return false;
            if (!(regexp.get("phoneTypePattern").matcher(numberParts[0].trim()).find() &&
                    regexp.get("phoneNumberPattern").matcher(numberParts[1].trim()).find()))
                return false;
        }
        return true;
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        String[] phonesStrings = data.split(";");
        for (String phoneData : phonesStrings) {
            contact.addPhoneNumber(parseOnePhoneNumber(phoneData.trim()));
        }
    }

    private PhoneNumber parseOnePhoneNumber(String phoneNumberData) {
        String[] splitData = phoneNumberData.split(" ");
        return new PhoneNumber(splitData[1], PhoneType.valueOf(splitData[0]));
    }
}
