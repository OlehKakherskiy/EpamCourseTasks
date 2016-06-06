package model.entity;

import controller.ValidationException;
import controller.validator.AbstractValidator;
import model.entity.credit.penaltyStrategy.PenaltyCalculationStrategy;

import java.util.Map;

/**
 * This class is a root of hierarchy of bank's operations, represents operation
 * with name {@link #name}, that could be processed by bank with specific
 * {@link #bankName}. Can give a description of operation, description about
 * documents needed to process this operation, and process the operation if all
 * documents are sent to input params of method. Also it delegates logic of
 * verifying documents before processing bank operation and calculation penalties,
 * when one side of bank operation hasn't implemented order's conditions.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see PenaltyCalculationStrategy
 * @see
 */
public abstract class BankOperation {

    /**
     * bank operation name (not a type of operation). Couldn't be changed
     */
    protected final String name;

    /**
     * Bank name, that provides this bank operation. Couldn't be changed
     */
    protected final String bankName;

    /**
     * processes penalty because one side of bank operation hasn't implemented
     * the order conditions.
     */
    protected PenaltyCalculationStrategy penaltyCalculationStrategy;

    /**
     * required documents for processing bank operation
     */
    protected Map<String, Class> requiredDocuments;

    protected AbstractValidator<Map<String, Class>, Map<String, Object>> documentsValidator;

    /**
     * Inits new bank operation with specific name and bank name
     *
     * @param name     operation name (not a type)
     * @param bankName bank name, that provides implementation of this operation
     */
    public BankOperation(String name, String bankName,
                         AbstractValidator<Map<String, Class>, Map<String, Object>> documentsValidator) {
        this.name = name;
        this.bankName = bankName;
        this.documentsValidator = documentsValidator;
    }

    /**
     * returns formatted details about this bank operation. Includes
     * also some details about penalties, required documents
     *
     * @return formatted details about bank operation
     */
    public abstract String getOfferDetails();

    /**
     * returns formatted details about required documents for
     * processing this bank operation.
     *
     * @return formatted documents description
     */
    public abstract String getRequiredDocumentsDescription();

    /**
     * creates bank operation order for client using documents from input parameter.
     * Firstly, validates actual documents with expected, using {@link #documentsValidator}.
     * After successful validation business logic should be implemented in
     * {@link #processBankOperationHook(Map)} by subclasses.
     *
     * @param documents client's documents, needed to order this bank operation
     * @return true, if bank operation processed successfully. Otherwise - false
     */
    public final boolean processBankOperation(Map<String, Object> documents) throws ValidationException {
        documentsValidator.validate(requiredDocuments, documents);
        return processBankOperationHook(documents);
    }

    /**
     * This method should be implemented by subclasses with specific business logic
     * of processing bank operation. If some problems occurs with processing bank
     * operation, should return false.
     * <p>
     * Each value from documents may be cast without problems because of successful
     * validation.
     * </p>
     *
     * @param documents documents, needed for processing this bank operation
     * @return true, if bank operation processed successfully. Otherwise - false
     * (some system problems may occur)
     */
    protected abstract boolean processBankOperationHook(Map<String, Object> documents);

    /**
     * processes penalty to result of banking operation because of not implemented
     * conditions by one of bank operation's side. The penalty causes are in the
     * input parameters.
     *
     * @param penaltyCauses causes why penalty should be processed
     */
    public void addPenaltyToOperationResult(Map<String, Object> penaltyCauses) throws ValidationException {
        penaltyCalculationStrategy.addPenaltyToBankOperation(penaltyCauses);
    }

    /**
     * sets the specific {@link PenaltyCalculationStrategy}.
     *
     * @param penaltyCalculationStrategy specific penalty calculation strategy. If null, do nothing
     */
    public void setPenaltyCalculationStrategy(PenaltyCalculationStrategy penaltyCalculationStrategy) {
        if (penaltyCalculationStrategy == null)
            return;
        this.penaltyCalculationStrategy = penaltyCalculationStrategy;
    }

    public String getName() {
        return name;
    }

    public String getBankName() {
        return bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankOperation)) return false;

        BankOperation that = (BankOperation) o;

        if (!name.equals(that.name)) return false;
        return bankName.equals(that.bankName) &&
                (penaltyCalculationStrategy != null ?
                        penaltyCalculationStrategy.equals(that.penaltyCalculationStrategy) :
                        that.penaltyCalculationStrategy == null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + bankName.hashCode();
        result = 31 * result + (penaltyCalculationStrategy != null ?
                penaltyCalculationStrategy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ",\npenaltyCalculationStrategy=" + penaltyCalculationStrategy;
    }
}