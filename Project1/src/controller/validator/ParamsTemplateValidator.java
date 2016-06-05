package controller.validator;

import app.GlobalContext;
import controller.ValidationException;

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
        for (Map.Entry<String, Class> currentExpectation : expectation.entrySet()) {
            Object value = actual.get(currentExpectation.getKey());
            if (value != null && !hasMatchValue(currentExpectation, value)) {
                String exceptionTemplate = (String) GlobalContext.getParam(GlobalContext.incompatibleTypesExceptionKey);

                throw new ValidationException(String.format(exceptionTemplate, currentExpectation.getKey(),
                        currentExpectation.getValue(), value.getClass()));
            }
        }
        removeUnexpectedParams(expectation, actual);
    }

    private void removeUnexpectedParams(Map<String, Class> expectation, Map<String, Object> actual) {
        actual.keySet().stream().filter(actualKey -> !expectation.containsKey(actualKey)).forEach(actual::remove);
    }

    private boolean hasMatchValue(Map.Entry<String, Class> expectationPattern, Object actualValue) {
        boolean isMatch = expectationPattern.getValue().isAssignableFrom(actualValue.getClass());
        if (!isMatch) {
            actualValue = expectationPattern.getValue().cast(actualValue);
            isMatch = expectationPattern.getValue().isAssignableFrom(actualValue.getClass());
        }
        return isMatch;
    }
}