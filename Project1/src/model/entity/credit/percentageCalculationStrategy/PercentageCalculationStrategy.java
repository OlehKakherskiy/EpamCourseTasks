package model.entity.credit.percentageCalculationStrategy;

import model.entity.credit.Credit;

/**
 * Class represents a root of percentage calculation hierarchy.
 * This hierarchy encapsulates logic of calculation percentages from
 * using credit by user. Each strategy parametrised by specific
 * {@link Credit} generic field, because strategy should have
 * access to credit's state on compilation stage.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class PercentageCalculationStrategy<T extends Credit> {

    /**
     * target credit, which percentage amount will be calculated for
     */
    protected T credit;

    /**
     * inits {@link #credit} field
     *
     * @param credit specific {@link Credit} subclass object
     */
    public PercentageCalculationStrategy(T credit) {
        this.credit = credit;
    }

    /**
     * method should calculate percentage amount of money for specific credit
     */
    public abstract void addPercentagePaymentToSum();

    @Override
    public String toString() {
        return "PercentageCalculationStrategy{" +
                "credit=" + credit.getName() + "of bank=" + credit.getBankName() + '}';
    }
}