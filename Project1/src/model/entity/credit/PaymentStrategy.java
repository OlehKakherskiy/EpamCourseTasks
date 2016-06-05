package model.entity.credit;

/**
 * Represents credit payment strategies.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public enum PaymentStrategy {

    /**
     * pays equal amount of money plus credit percentage each paying period
     */
    DIFFERENTIATED,

    /**
     * pays equal amount of money each paying period (as result, pays more money, then
     * with {@link #DIFFERENTIATED} payment strategy
     */
    ANNUITY,

    /**
     * credit's user pays full credit amount plus percents once
     */
    FULLY
}