package controller.validator;

import controller.ValidationException;

/**
 * Class represents root of parameters validation hierarchy.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractValidator<T, E> {

    /**
     * next validation chain. May be null
     */
    private AbstractValidator<T, E> nextChain;

    public AbstractValidator(AbstractValidator<T, E> nextChain) {
        this.nextChain = nextChain;
    }

    /**
     * Validates actual params to target expectations. If this class validates params successfully,
     * then, if {@link #nextChain} is not null, also do validation.
     *
     * @param expectation validation condition
     * @param actual      value, that should be validated
     * @throws ValidationException
     */
    public final void validate(T expectation, E actual) throws ValidationException {
        validateHook(expectation, actual);
        if (nextChain != null) {
            nextChain.validate(expectation, actual);
        }
    }

    /**
     * encapsulates validation algorithm of actual params to target expectations.
     *
     * @param expectation validation condition
     * @param actual      param that should be validated
     * @throws ValidationException if actual params are not valid to current expectations
     */
    protected abstract void validateHook(T expectation, E actual) throws ValidationException;
}
