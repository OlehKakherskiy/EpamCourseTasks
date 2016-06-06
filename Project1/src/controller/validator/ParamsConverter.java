package controller.validator;

import controller.ValidationException;
import model.entity.credit.PaymentStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class, converts String input parameters according to special expected templates.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ParamsConverter {

    /**
     * coverts actual params according to template expectations. If parameter can't be converted, throws
     * Validation exception. If there's no expected template for actual param - do nothing. Converts just
     * those params, that has actual type - {@link String} and it differs from expected type.
     *
     * @param template expected types of actual params
     * @param actual   actual params, that should be converted to expected types
     * @return actual params, converted to expected types
     * @throws ValidationException if actual param can't be converted
     */
    public static Map<String, Object> convertParams(Map<String, Class> template, Map<String, Object> actual)
            throws ValidationException {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : actual.entrySet()) {
            String key = entry.getKey();
            if (template.get(key) != null && template.get(key).equals(String.class)) {
                result.put(key, tryConvert((String) entry.getValue(), template.get(key)));
            } else {
                result.put(key, entry.getValue());
            }
        }
        return result;
    }

    /**
     * Converts value to expectation type. Works with {@link Integer}, {@link Double}, {@link Boolean},
     * {@link PaymentStrategy} types. If template is null - do nothing
     *
     * @param value    value, that should be converted to expected type
     * @param template expectation type of convertation
     * @return converted value, if it could be converted
     * @throws ValidationException if value can't ve converted
     */
    private static Object tryConvert(String value, Class template) throws ValidationException {
        if (template == null)
            return value;
        try {
            switch (template.getName()) {
                case "java.lang.Integer": {
                    return Integer.valueOf(value);
                }
                case "java.lang.Double": {
                    return Double.parseDouble(value);
                }
                case "java.lang.Boolean": {
                    return Boolean.parseBoolean(value);
                }
                case "model.entity.credit.PaymentStrategy": {
                    return PaymentStrategy.valueOf(value);
                }
                default:
                    return value;
            }
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }
}