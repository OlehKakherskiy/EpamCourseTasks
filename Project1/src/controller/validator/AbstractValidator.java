package controller.validator;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractValidator<T, E> {

    private AbstractValidator<T, E> nextChain;

    public AbstractValidator(AbstractValidator<T, E> nextChain) {
        this.nextChain = nextChain;
    }

    public final boolean validate(T expectation, E actual) {
        return validateHook(expectation, actual) &&
                (nextChain == null || nextChain.validate(expectation, actual));
    }

    protected abstract boolean validateHook(T expectation, E actual);
}
