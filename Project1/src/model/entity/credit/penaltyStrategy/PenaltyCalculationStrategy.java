package model.entity.credit.penaltyStrategy;

import app.GlobalContext;
import controller.ValidationException;
import controller.validator.AbstractValidator;
import model.entity.BankOperation;

import java.util.Map;

/**
 * Class represents a hierarchy's root of calculations penalties
 * strategies for specific bank's operation. Each strategy should calculate
 * penalty if at least one penalty's cause occurs.
 * <p>
 * Each strategy has specific cause patterns, that are used when
 * {@link #addPenaltyToBankOperation(Map)} is invoked. First of all, the condition
 * of at least one penalty's cause presence is checked by instance of
 * {@link AbstractValidator}. Then, if the condition is true, penalty will be
 * processed. Otherwise there's no important causes to calculate penalty.
 * <p>
 * Each strategy may need full access to specific bank operation for processing
 * penalty. So that it's added generic field for having access to specific
 * subclass of {@link BankOperation} at compilation stage.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see AbstractValidator
 */
public abstract class PenaltyCalculationStrategy<T extends BankOperation> {

    /**
     * specific bank operation, for which penalty will be processed
     */
    protected T bankOperation;

    /**
     * penalty causes, that invokes penalty processing for this
     * bank's operation
     */
    protected Map<String, Class> penaltyCausesPatterns;

    /**
     * validator, that validates causes for penalty calculation
     */
    protected AbstractValidator penaltyCauseValidator;

    public PenaltyCalculationStrategy(T bankOperation, Map<String, Class> penaltyCausesPatterns,
                                      AbstractValidator penaltyValidator) {
        this.bankOperation = bankOperation;
        this.penaltyCausesPatterns = penaltyCausesPatterns;
        this.penaltyCauseValidator = GlobalContext.getDefaultValidator(penaltyCausesPatterns, penaltyValidator);
    }

    /**
     * processes penalty addition to specific bank operation. Firstly, validates
     * the penalty causes, using {@link #penaltyCauseValidator} and only if validation
     * is successful - processes the penalty addition to bank operation.
     *
     * @param penaltyCauses causes why penalty should be processed
     */
    public final void addPenaltyToBankOperation(Map<String, Object> penaltyCauses) throws ValidationException {
        penaltyCauseValidator.validate(penaltyCauses);
        addPenaltyHook(penaltyCauses);
    }

    /**
     * encapsulates specific logic of processing penalty for bank operation.
     *
     * @param penaltyCauses causes why penalty should be processed
     */
    protected abstract void addPenaltyHook(Map<String, Object> penaltyCauses);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PenaltyCalculationStrategy)) return false;

        PenaltyCalculationStrategy<?> that = (PenaltyCalculationStrategy<?>) o;

        if (!bankOperation.equals(that.bankOperation)) return false;
        return penaltyCausesPatterns.equals(that.penaltyCausesPatterns);

    }

    @Override
    public int hashCode() {
        int result = bankOperation.hashCode();
        result = 31 * result + penaltyCausesPatterns.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "PenaltyCalculationStrategy:" +
                "bankOperation=" + bankOperation.toString() +
                ", penaltyCausesPatterns=" + penaltyCausesPatterns;
    }
}