package controller.validator;

import app.GlobalContext;
import controller.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class encapsulates validation different key/value params count, for there validation
 * there's key/value templates, which value are {@link Class} objects. Validates whether
 * at least one actual param matches to validation template. Also, before validation, tries
 * to convert all String params to target template types.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see ParamsConverter
 */
public class ParamsTemplateValidator extends AbstractValidator<Map<String, Class>, Map<String, Object>> {

    public ParamsTemplateValidator(AbstractValidator<Map<String, Class>, Map<String, Object>> nextChain) {
        super(nextChain);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes all unexpected actual params, then tries to convert all String params
     * to target types using {@link ParamsConverter}. Then checks comparability between target types and actual.
     *
     * @param expectation target keys and types expectations of actual params
     * @param actual      actual key/value params
     * @throws ValidationException if converting was unsuccessful or if actual param's type
     *                             and expectation type are incompatible
     */
    @Override
    protected void validateHook(Map<String, Class> expectation, Map<String, Object> actual) throws ValidationException {

        removeUnexpectedParams(expectation, actual);
        if (actual.size() == 0) {
            throw new ValidationException("Validation exception. " +
                    "No matches between expectation templates and actual params");
        }

        actual = ParamsConverter.convertParams(expectation, actual);

        for (Map.Entry<String, Object> currentEntry : actual.entrySet()) {
            if (!isCompatibleTypes(currentEntry.getKey(), expectation.get(currentEntry.getKey()), currentEntry.getValue())) {
                String exceptionTemplate = (String) GlobalContext.getParam(GlobalContext.incompatibleTypesExceptionKey);

                throw new ValidationException(String.format(exceptionTemplate, currentEntry.getKey(),
                        expectation.get(currentEntry.getKey()), currentEntry.getValue().getClass()));
            }
        }
    }

    /**
     * removes all extra params, that are not expected by expectation param's templates.
     *
     * @param expectation params templates
     * @param actual      actual params
     */
    private void removeUnexpectedParams(Map<String, Class> expectation, Map<String, Object> actual) {
        Map<String, Object> forRemoving = new HashMap<>();
        actual.keySet().stream().filter(key -> !expectation.containsKey(key)).forEach(key -> forRemoving.put(key, actual.get(key)));
        forRemoving.keySet().forEach(actual::remove);
    }

    /**
     * checks whether actual value is a subtype of expectation pattern's type
     *
     * @param expectationPattern expectation type
     * @param actualValue        actual object
     * @return true, if actual object's class is a subclass, or the same as
     * expectation type
     * @throws ValidationException if actual param's value is null
     */
    private boolean isCompatibleTypes(String key, Class expectationPattern, Object actualValue) throws ValidationException {
        if (actualValue == null)
            throw new ValidationException("actual param with key:" + key + "is null.");
        return expectationPattern.isAssignableFrom(actualValue.getClass());
    }
}