package controller.validator;

import controller.ValidationException;

import java.util.Map;

/**
 * Class represents root of parameters validation hierarchy.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractValidator {

    /**
     * next validation chain. May be null
     */
    private AbstractValidator nextChain;

    public AbstractValidator(AbstractValidator nextChain) {
        this.nextChain = nextChain;
    }

    /**
     * Validates actual params to target expectations. If this class validates params successfully,
     * then, if {@link #nextChain} is not null, also do validation.
     *
     * @param actual value, that should be validated
     * @throws ValidationException
     */
    public final void validate(Map<String, Object> actual) throws ValidationException {
        validateHook(actual);
        if (nextChain != null) {
            nextChain.validate(actual);
        }
    }

    /**
     * encapsulates validation algorithm of actual params to target expectations.
     *
     * @param actual param that should be validated
     * @throws ValidationException if actual params are not valid to current expectations
     */
    protected abstract void validateHook(Map<String, Object> actual) throws ValidationException;
}
