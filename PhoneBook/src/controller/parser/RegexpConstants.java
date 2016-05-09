package controller.parser;

import entity.ContactGroup;
import entity.PhoneType;

import java.util.regex.Pattern;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class RegexpConstants {

    public static final Pattern FULL_NAME_PATTERN = Pattern.compile("^([A-Z][a-z]+) ([A-Z][a-z]+) ([A-Z][a-z]+)");

    public static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(0\\d{1,4})(\\d{3})(\\d{2})(\\d{2})");

    public static final Pattern MOCK_PATTERN = Pattern.compile(".*");

    public static final Pattern CONTACT_GROUP_PATTERN = Pattern.compile(formatContactGroupPattern());

    public static final Pattern PHONE_TYPE_PATTERN = Pattern.compile(formatPhoneTypePattern());

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+@[a-z]+\\.[a-z]+");

    public static final Pattern INDEX_PATTERN = Pattern.compile("^\\d{5}");

    public static final Pattern FLAT_NUMBER_PATTERN = Pattern.compile("^\\d{1,3}");

    public static final Pattern STREET_NUMBER_PATTERN = Pattern.compile("^\\d{1,3}|\\d/\\d{1,3}");

    public static final Pattern COUNTRY_PATTERN = Pattern.compile("^[A-Z][a-z]+");

    public static final Pattern REGION_PATTERN = COUNTRY_PATTERN;

    public static final Pattern CITY_PATTERN = COUNTRY_PATTERN;

    public static final Pattern STREET_NAME_PATTERN = Pattern.compile("^[a-zA-Z]+|([a-zA-Z]+ [a-zA-Z]+)");


    private static String formatContactGroupPattern() {
        StringBuilder result = new StringBuilder("^");

        for (ContactGroup group : ContactGroup.values()) {
            result.append(group.name()).append("|");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private static String formatPhoneTypePattern() {
        StringBuilder result = new StringBuilder("^");
        for (PhoneType type : PhoneType.values())
            result.append(type.name()).append("|");
        return result.deleteCharAt(result.length() - 1).toString();
    }
}
