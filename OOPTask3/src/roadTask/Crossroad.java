package roadTask;

/**
 * Class represents crossroad checkpoint, that stands on crossing of two streets, name of each has to
 * be specified.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Crossroad extends AbstractCheckPoint {

    /**
     * street name
     */
    private String street1;

    /**
     * another street name
     */
    private String street2;

    /**
     * creates new crossroad checkpoint. Adds streets names to this crossroad object. Before addition
     * checks whether streets names aren't null or empty, or not equal to each other
     *
     * @param street1 specific street name
     * @param street2 another specific street name
     */
    public Crossroad(String street1, String street2) {
        super();
        if (street1 == null || street1.equals("") || street2 == null || street2.equals("") || street1.equals(street2))
            throw new IllegalArgumentException("street names shouldn't be empty or null or equal to each other");
        this.street1 = street1;
        this.street2 = street2;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }
}
