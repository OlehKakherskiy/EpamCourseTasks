package model;

import model.entity.credit.Credit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class is a storage of bank's credits.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CreditList {

    /**
     * all credits, that are offered for client from banks
     */
    private Set<Credit> credits;

    public CreditList() {
        credits = new HashSet<>();
    }

    public void addCredits(Credit... credits) { //TODO: nullCheck
        this.credits.addAll(Arrays.asList(credits));
    }

    public Set<Credit> getCredits() {
        return credits;
    }
}