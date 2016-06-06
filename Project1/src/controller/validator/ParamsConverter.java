package controller.validator;

import controller.ValidationException;
import model.entity.credit.PaymentStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ParamsConverter {

    public static Map<String, Object> convertParams(Map<String, Class> template, Map<String, Object> actual)
            throws ValidationException {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : actual.entrySet()) {
            String key = entry.getKey();
            if (template.get(key) != null && template.get(key).equals(String.class)) {
                result.put(key, tryConvert(entry, template.get(key)));
            } else {
                result.put(key, entry.getValue());
            }
        }
        return result;
    }

    private static Object tryConvert(Map.Entry<String, Object> entry, Class template) throws ValidationException {
        String value = (String) entry.getValue();
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