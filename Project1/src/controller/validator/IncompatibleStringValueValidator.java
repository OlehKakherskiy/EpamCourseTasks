package controller.validator;

import app.GlobalContext;
import controller.ValidationException;

import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class IncompatibleStringValueValidator extends ParamsTemplateValidator {

    public IncompatibleStringValueValidator(AbstractValidator<Map<String, Class>, Map<String, Object>> nextChain) {
        super(nextChain);
    }

    @Override
    protected void validateHook(Map<String, Class> expectation, Map<String, Object> actual) throws ValidationException {
        super.validateHook(expectation, actual);
        for (Map.Entry<String, Class> entry : expectation.entrySet()) {
            if (entry.getValue().equals(String.class)) {
                String key = entry.getKey();
                String value = (String) actual.get(key);
                nullCheck(key, value);
                emptyCheck(key, value);
            }
        }
    }

    private void nullCheck(String key, String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException(String.format((String) GlobalContext.getParam
                    (GlobalContext.nullStringValidationException), key));
        }
    }

    private void emptyCheck(String key, String value) throws ValidationException {
        if (value.equals("")) {
            throw new ValidationException(String.format((String) GlobalContext.getParam
                    (GlobalContext.emptyStringValidationException), key));
        }
    }
}
