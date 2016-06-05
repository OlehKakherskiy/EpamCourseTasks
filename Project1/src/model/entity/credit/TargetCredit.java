package model.entity.credit;

import controller.validator.AbstractValidator;

import java.util.Map;

/**
 * Class represents target credit operation. This type of credit
 * have specific target, which bank is processing this credit for,
 * and potential opportunity to do payout earlier then {@link #maxMonthCount}.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class TargetCredit extends Credit {

    /**
     * opportunity to payout credit earlier then {@link #maxMonthCount}
     */
    private boolean earlyRepaymentPossibility;

    /**
     * credit's purpose
     */
    private String creditTarget;

    public TargetCredit(String name, String bankName, int maxMonthCount, int minSumAmount, int maxSumAmount,
                        boolean earlyRepaymentPossibility, String creditTarget,
                        AbstractValidator<Map<String, Class>, Map<String, Object>> documentsValidator) {
        super(name, bankName, maxMonthCount, minSumAmount, maxSumAmount, documentsValidator);
        this.earlyRepaymentPossibility = earlyRepaymentPossibility;
        this.creditTarget = creditTarget;
    }

    @Override
    public String getOfferDetails() {
        return "";
    }

    @Override
    public String getRequiredDocumentsDescription() {
        return "";
    }

    @Override
    public boolean processBankOperationHook(Map<String, Object> documents) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTargetCredit{" +
                "earlyRepaymentPossibility=" + earlyRepaymentPossibility +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetCredit)) return false;
        if (!super.equals(o)) return false;

        TargetCredit that = (TargetCredit) o;
        return earlyRepaymentPossibility == that.earlyRepaymentPossibility;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (earlyRepaymentPossibility ? 1 : 0);
        return result;
    }
}
