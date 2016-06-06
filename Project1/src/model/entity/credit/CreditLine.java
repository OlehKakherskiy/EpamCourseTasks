package model.entity.credit;

import controller.validator.AbstractValidator;

import java.util.Map;

/**
 * Class represents a credit line type of credit.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CreditLine extends Credit {

    /**
     * represents whether {@link #maxSumAmount} of this credit line
     * can be increased.
     */
    private boolean increaseMaxCreditLimitOpportunity;

    public CreditLine(String name, String bankName, int maxMonthCount, int minSumAmount, int maxSumAmount,
                      boolean increaseMaxCreditLimitOpportunity,
                      AbstractValidator<Map<String, Class>, Map<String, Object>> documentsValidator,
                      PaymentStrategy paymentStrategy) {
        super(name, bankName, maxMonthCount, minSumAmount, maxSumAmount, documentsValidator, paymentStrategy);
        this.increaseMaxCreditLimitOpportunity = increaseMaxCreditLimitOpportunity;
    }


    @Override
    public String getOfferDetails() {
        return "offer details: " + toString();
    }

    @Override
    public String getRequiredDocumentsDescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean processBankOperationHook(Map<String, Object> documents) {
        throw new UnsupportedOperationException();
    }

    /**
     * represents whether {@link #maxSumAmount} of this credit line
     * can be increased.
     *
     * @return true if {@link #maxSumAmount} of this credit line
     * can be increased. Otherwise - false.
     */
    public boolean isUpgradeMaxCreditLimitOpportunity() {
        return increaseMaxCreditLimitOpportunity;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCreditLine{" +
                "upgradeMaxCreditLimitAbility=" + increaseMaxCreditLimitOpportunity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditLine)) return false;
        if (!super.equals(o)) return false;

        CreditLine that = (CreditLine) o;

        return increaseMaxCreditLimitOpportunity == that.increaseMaxCreditLimitOpportunity;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (increaseMaxCreditLimitOpportunity ? 1 : 0);
        return result;
    }

    /**
     * increases {@link #maxSumAmount} only if
     * credit line can be increased and current param is bigger
     * than {@link #maxSumAmount}
     *
     * @param maxSumAmount max credit's amount of money
     */
    @Override
    public void setMaxSumAmount(int maxSumAmount) {
        if (increaseMaxCreditLimitOpportunity && maxSumAmount > this.maxSumAmount) {
            super.setMaxSumAmount(maxSumAmount);
        }
    }
}