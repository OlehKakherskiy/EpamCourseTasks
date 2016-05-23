/**
 * Util class that has methods and exceptions messages for checking fields for null and emptiness
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public final class CheckUtils {

    public static final String nullCheckException = "Field %s can't be null";

    public static final String emptinessCheckException = "Field %s can't contain empty string";

    public static void nullCheck(Object o, String fieldName) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException(String.format(nullCheckException, fieldName));
        }
    }

    public static void emptinessCheck(String fieldValue, String fieldName) {
        if (fieldName.equals("")) {
            throw new IllegalArgumentException(String.format(emptinessCheckException, fieldName));
        }
    }

    public static void fullCheck(Object o, String fieldName) {
        nullCheck(o, fieldName);
        if (o instanceof String) {
            emptinessCheck((String) o, fieldName);
        }
    }
}
