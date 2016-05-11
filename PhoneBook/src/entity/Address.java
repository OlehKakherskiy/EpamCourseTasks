package entity;

/**
 * Class represents address entity. Full address, that can unique identify every street,
 * consists of postal index, country name, region (can be region, state, etc.), city name, street name,
 * street number, flat number.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Address {

    /**
     * country name
     */
    private String country;

    /**
     * region name
     */
    private String region;

    /**
     * city name
     */
    private String city;

    /**
     * street name
     */
    private String street;

    /**
     * house number
     */
    private String houseNumber;

    /**
     * flat number
     */
    private String flatNumber;

    /**
     * post index
     */
    private String index;

    /**
     * <p>
     * Formats Address in postal format.
     * </p>
     * <p>
     * Format:
     * </p>
     * <p>
     * street name, house number, flat number
     * </p>
     * <p>
     * city
     * </p>
     * <p>
     * region
     * </p>
     * <p>
     * country
     * </p>
     * <p>
     * index
     * </p>
     *
     * @return string representation of full address in postal format
     */
    public String formatAsPostAddress() {
        return new StringBuilder(street).append(", ").append(houseNumber).
                append(", ").append(flatNumber).append("\n").
                append(city).append("\n").append(region).
                append("\n").append(country).append("\n").
                append(index).toString();
    }

    public String formatFullAddress() {
        return null;
        //TODO:реализация метода!!
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getIndex() {
        return index;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }
}
