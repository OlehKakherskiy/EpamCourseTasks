package controller.validator;

import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ParamsTemplateValidator extends AbstractValidator<Map<String, Class>, Map<String, Object>> {

    public ParamsTemplateValidator(AbstractValidator<Map<String, Class>, Map<String, Object>> nextChain) {
        super(nextChain);
    }

    @Override
    protected boolean validateHook(Map<String, Class> expectation, Map<String, Object> actual) {
        boolean hasMatchCause = false;
        for (Map.Entry<String, Class> currentExpectation : expectation.entrySet()) {
            Object value = actual.get(currentExpectation.getKey());
            if (value != null) {
                hasMatchCause = true;
                if (!hasMatchValue(currentExpectation, value)) {
                    return false;
                }
            }
        }
        removeUnexpectedParams(expectation, actual);
        return hasMatchCause;
    }

    private void removeUnexpectedParams(Map<String, Class> expectation, Map<String, Object> actual) {
        actual.keySet().stream().filter(actualKey -> !expectation.containsKey(actualKey)).forEach(actual::remove);
    }

    private boolean hasMatchValue(Map.Entry<String, Class> expectationPattern, Object actualValue) {
        return expectationPattern.getValue().isAssignableFrom(actualValue.getClass());
    }

}
