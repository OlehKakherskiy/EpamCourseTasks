package controller.validator;

import app.GlobalContext;
import controller.ValidationException;

import java.util.Map;

/**
 * Validator checks, whether String values, sent as actual values of validation, are not null and not empty
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class IncompatibleStringValueValidator extends ParamsTemplateValidator {

    public IncompatibleStringValueValidator(AbstractValidator<Map<String, Class>, Map<String, Object>> nextChain) {
        super(nextChain);
    }

    /**
     * {@inheritDoc}
     * <p>
     * for each actual param, that should be String according to expectations,
     * do null check and check for emptiness.
     * </p>
     *
     * @param expectation target keys and types expectations of actual params
     * @param actual      actual key/value params
     * @throws ValidationException if actual string param is null or empty
     */
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

    /**
     * checks whether value ins't null.
     *
     * @param key   key of the actual parameter
     * @param value value of the actual parameter
     * @throws ValidationException if value is null
     */
    private void nullCheck(String key, String value) throws ValidationException {
        if (value == null) {
            throw new ValidationException(String.format((String) GlobalContext.getParam
                    (GlobalContext.nullStringValidationException), key));
        }
    }

    /**
     * checks whether value ins't empty.
     *
     * @param key   key of the actual parameter
     * @param value value of the actual parameter
     * @throws ValidationException if value is empty
     */
    private void emptyCheck(String key, String value) throws ValidationException {
        if (value.equals("")) {
            throw new ValidationException(String.format((String) GlobalContext.getParam
                    (GlobalContext.emptyStringValidationException), key));
        }
    }
}