import controller.Controller;
import controller.parser.*;
import model.NoteBook;
import view.MessageTemplates;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        List<AbstractDataParser> parsers = new ArrayList<>();
        Map<String, Pattern> patternMap = new HashMap<>();

        patternMap.put("fullNamePattern", RegexpConstants.FULL_NAME_PATTERN);
        patternMap.put("contactGroupPattern", RegexpConstants.CONTACT_GROUP_PATTERN);
        parsers.add(new FullNameParser(patternMap, MessageTemplates.INPUT_FULL_NAME,
                formatErrorMessage("Full name should have format: <surname> <name> <second name> <contact_group>")));

        patternMap = new HashMap<>();
        patternMap.put("phoneTypePattern", RegexpConstants.PHONE_TYPE_PATTERN);
        patternMap.put("phoneNumberPattern", RegexpConstants.PHONE_NUMBER_PATTERN);
        parsers.add(new PhoneParser(patternMap, MessageTemplates.INPUT_PHONE,
                formatErrorMessage("Phone should have format: <phone_type> <phone_number>")));

        patternMap = new HashMap<>();
        patternMap.put("nickNamePattern", RegexpConstants.MOCK_PATTERN);
        parsers.add(new NicknameParser(patternMap, MessageTemplates.INPUT_NICKNAME,
                formatErrorMessage("Nickname shouldn't be empty")));

        patternMap = new HashMap<>();
        patternMap.put("emailPattern", RegexpConstants.EMAIL_PATTERN);
        parsers.add(new EmailParser(patternMap, MessageTemplates.INPUT_EMAIL,
                formatErrorMessage("Email should consist of nickname, symbol '@' and domain_name")));

        patternMap = new HashMap<>();
        patternMap.put("skypePattern", RegexpConstants.MOCK_PATTERN);
        parsers.add(new SkypeParser(patternMap, MessageTemplates.INPUT_SKYPE,
                formatErrorMessage("Skype nickname shouldn't be empty")));

        patternMap = new HashMap<>();
        patternMap.put("commentPattern", RegexpConstants.MOCK_PATTERN);
        parsers.add(new CommentParser(patternMap, MessageTemplates.INPUT_COMMENT, formatErrorMessage("")));

        patternMap = new HashMap<>();
        patternMap.put("countryPattern", RegexpConstants.COUNTRY_PATTERN);
        patternMap.put("regionPattern", RegexpConstants.REGION_PATTERN);
        patternMap.put("cityPattern", RegexpConstants.CITY_PATTERN);
        patternMap.put("streetPattern", RegexpConstants.STREET_NAME_PATTERN);
        patternMap.put("streetNumberPattern", RegexpConstants.STREET_NUMBER_PATTERN);
        patternMap.put("flatNumberPattern", RegexpConstants.FLAT_NUMBER_PATTERN);
        patternMap.put("indexPattern", RegexpConstants.INDEX_PATTERN);
        parsers.add(new AddressParser(patternMap, MessageTemplates.INPUT_FULL_ADDRESS,
                formatErrorMessage("Address format should be:\n" +
                        "country, region, city, street, house_number, flat_number, index")));
        View view = new View();
        NoteBook noteBook = new NoteBook();
        Controller controller = new Controller(view, noteBook, parsers);
        controller.processUser();
    }

    private static String formatErrorMessage(String message) {
        return String.format(MessageTemplates.INPUT_ERROR, message);
    }
}
