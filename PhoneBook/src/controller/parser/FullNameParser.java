package controller.parser;

import entity.ContactGroup;
import entity.ContactName;
import model.Contact;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FullNameParser extends AbstractDataParser {

    public FullNameParser(Map<String, Pattern> regexp, String parsingRulesMessage, String errorMessage) {
        super(regexp, parsingRulesMessage, errorMessage);
    }

    @Override
    protected boolean applyCheckStrategy(String data) {
        System.out.println(data);
        System.out.println(regexp.get("contactGroupPattern").matcher(data).find());
        System.out.println(regexp.get("contactGroupPattern").pattern());
        return regexp.get("fullNamePattern").matcher(data).find() && regexp.get("contactGroupPattern").matcher(data).find();
    }

    @Override
    protected void completeModel(Contact contact, String data) {
        ContactName name = new ContactName();
        String[] splitFullName = data.split(" ");
        name.setSurname(splitFullName[0]);
        name.setName(splitFullName[1]);
        name.setSecondName(splitFullName[2]);
        contact.setContactName(name);
        contact.setContactGroup(ContactGroup.valueOf(splitFullName[3]));
    }
}
