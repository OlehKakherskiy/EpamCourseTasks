package model.entity.credit;

import controller.validator.AbstractValidator;
import model.entity.BankOperation;
import model.entity.credit.percentageCalculationStrategy.PercentageCalculationStrategy;

import java.util.Map;

/**
 * Class represents hierarchy's root of credits operations. It has
 * additional state representing payment and percentage calculation
 * strategies, period of credit's full payout, min and max amount of
 * money of specific credit.
 * <p>
 * Percentage calculation is delegated to specific {@link PercentageCalculationStrategy}
 * type
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see PercentageCalculationStrategy
 */
public abstract class Credit extends BankOperation {

    /**
     * specific payment strategy
     */
    protected PaymentStrategy paymentStrategy;

    /**
     * specific percentage calculation strategy
     */
    protected PercentageCalculationStrategy percentageCalculationStrategy;

    /**
     * max period of credit's payout (in month, >0)
     */
    protected int maxMonthCount;

    /**
     * min amount of money, that is offered by this credit
     * operation ( > 0)
     */
    protected int minSumAmount;

    /**
     * max amount of money, that is offered by this credit
     * operation (> {@link #minSumAmount})
     */
    protected int maxSumAmount;

    public Credit(String name, String bankName, int maxMonthCount, int minSumAmount, int maxSumAmount,
                  AbstractValidator<Map<String, Class>, Map<String, Object>> documentsValidator,
                  PaymentStrategy paymentStrategy) {
        super(name, bankName, documentsValidator);
        setMaxMonthCount(maxMonthCount);
        setMinSumAmount(minSumAmount);
        setMaxSumAmount(maxSumAmount);
        this.paymentStrategy = paymentStrategy == null ? PaymentStrategy.FULLY : paymentStrategy;
    }


    /**
     * adds percents to full payout for using this credit operation by user
     */
    public void addPercentageAmountToOperationResult() {
        percentageCalculationStrategy.addPercentagePaymentToSum();
    }

    /**
     * sets the specific percentage calculation strategy for this
     * credit operation
     *
     * @param percentageCalculationStrategy specific percentage calculation strategy.
     *                                      If null - do nothing.
     */
    public void setPercentageCalculationStrategy(PercentageCalculationStrategy percentageCalculationStrategy) {
        if (percentageCalculationStrategy == null)
            return;
        this.percentageCalculationStrategy = percentageCalculationStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public int getMaxMonthCount() {
        return maxMonthCount;
    }

    /**
     * sets the value of {@link #maxMonthCount}. If < 0 - do nothing
     *
     * @param maxMonthCount max month payout count
     */
    public void setMaxMonthCount(int maxMonthCount) {
        this.maxMonthCount = maxMonthCount > 0 ? maxMonthCount : this.maxMonthCount;
    }

    public int getMinSumAmount() {
        return minSumAmount;
    }

    /**
     * sets the value of {@link #minSumAmount} only if it
     * in range (0, {@link #maxSumAmount})
     *
     * @param minSumAmount minimal credit's amount of money
     */
    public final void setMinSumAmount(int minSumAmount) {
        if (minSumAmount > 0 && minSumAmount < maxSumAmount)
            this.minSumAmount = minSumAmount;
    }

    public int getMaxSumAmount() {
        return maxSumAmount;
    }

    /**
     * sets the value of {@link #maxSumAmount} only if
     * it > {@link #minSumAmount}
     *
     * @param maxSumAmount max credit's amount of money
     */
    public void setMaxSumAmount(int maxSumAmount) {
        if (minSumAmount < maxSumAmount)
            this.maxSumAmount = maxSumAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;
        if (!super.equals(o)) return false;

        Credit credit = (Credit) o;

        if (maxMonthCount != credit.maxMonthCount) return false;
        if (minSumAmount != credit.minSumAmount) return false;
        if (maxSumAmount != credit.maxSumAmount) return false;
        if (paymentStrategy != credit.paymentStrategy) return false;
        return percentageCalculationStrategy != null ? percentageCalculationStrategy.equals(credit.percentageCalculationStrategy) : credit.percentageCalculationStrategy == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + paymentStrategy.hashCode();
        result = 31 * result + (percentageCalculationStrategy != null ? percentageCalculationStrategy.hashCode() : 0);
        result = 31 * result + maxMonthCount;
        result = 31 * result + minSumAmount;
        result = 31 * result + maxSumAmount;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("Credit{" + "paymentStrategy=").append(paymentStrategy).
                append(", maxMonthCount=").append(maxMonthCount).
                append(", minSumAmount=").append(minSumAmount).
                append(", maxSumAmount=").append(maxSumAmount).
                append(",\npercentageCalculationStrategy=").append(percentageCalculationStrategy).append('}');
        return builder.toString();
    }
}