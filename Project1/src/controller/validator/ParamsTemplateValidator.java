package controller.validator;

import app.GlobalContext;
import controller.ValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ParamsTemplateValidator extends AbstractValidator<Map<String, Class>, Map<String, Object>> {

    public ParamsTemplateValidator(AbstractValidator<Map<String, Class>, Map<String, Object>> nextChain) {
        super(nextChain);
    }

    @Override
    protected void validateHook(Map<String, Class> expectation, Map<String, Object> actual) throws ValidationException {
        actual = ParamsConverter.convertParams(expectation, actual);
        for (Map.Entry<String, Class> currentExpectation : expectation.entrySet()) {
            Object value = actual.get(currentExpectation.getKey());
            if (value != null && !hasMatchValue(currentExpectation, value)) {
                String exceptionTemplate = (String) GlobalContext.getParam(GlobalContext.incompatibleTypesExceptionKey);

                throw new ValidationException(String.format(exceptionTemplate, currentExpectation.getKey(),
                        currentExpectation.getValue(), value.getClass()));
            }
        }
        removeUnexpectedParams(expectation, actual);
        if (actual.size() == 0) {
            throw new ValidationException("Validation exception. " +
                    "No matches between expectation templates and actual params");
        }
    }

    private void removeUnexpectedParams(Map<String, Class> expectation, Map<String, Object> actual) {
        Map<String, Object> forRemoving = new HashMap<>();
        actual.keySet().stream().filter(key -> !expectation.containsKey(key)).forEach(key -> forRemoving.put(key, actual.get(key)));
        forRemoving.keySet().forEach(actual::remove);
    }

    private boolean hasMatchValue(Map.Entry<String, Class> expectationPattern, Object actualValue) {
        return expectationPattern.getValue().isAssignableFrom(actualValue.getClass());
    }
}