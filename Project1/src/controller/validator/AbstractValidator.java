package controller.validator;

import controller.ValidationException;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractValidator<T, E> {

    private AbstractValidator<T, E> nextChain;

    public AbstractValidator(AbstractValidator<T, E> nextChain) {
        this.nextChain = nextChain;
    }

    public final void validate(T expectation, E actual) throws ValidationException {
        validateHook(expectation, actual);
        if (nextChain != null) {
            nextChain.validate(expectation, actual);
        }
    }

    protected abstract void validateHook(T expectation, E actual) throws ValidationException;
}
