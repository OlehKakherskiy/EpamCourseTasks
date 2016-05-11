package view;

import entity.ContactGroup;
import entity.PhoneType;

import java.util.Arrays;

/**
 * Simple messages with description how to input different types of data.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class MessageTemplates {

    public static final String INPUT_FULL_NAME = "Input full name in format: surname name second_name " +
            Arrays.toString(ContactGroup.values());

    public static final String INPUT_NICKNAME = "Input nickname";

    public static final String INPUT_FULL_ADDRESS = "Input address in format: " +
            "country, region, city, street, house_number, flat_number, index";

    public static final String INPUT_PHONE = "Input phones in format: phone_type phone_number; another phone in the same" +
            "format, if need. Phone types: " + Arrays.toString(PhoneType.values());

    public static final String INPUT_EMAIL = "Input email address";

    public static final String INPUT_SKYPE = "Input skype nickname";

    public static final String INPUT_COMMENT = "Input comment, if need";

    /**
     * error template string. Should be used as a parameter
     */
    public static final String INPUT_ERROR = "Error with inputting data.%s.Try again";
}
