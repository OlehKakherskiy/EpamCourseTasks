package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Address {

    private String country;

    private String region;

    private String city;

    private String street;

    private String houseNumber;

    private String flatNumber;

    private String index;

    public Address(String country, String region, String city, String street, String houseNumber,
                   String flatNumber, String index) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.index = index;
    }

    public Address() {
    }

    public String formatAsPostAddress() {
        return new StringBuilder(street).append(", ").append(houseNumber).
                append(", ").append(flatNumber).append("\n").
                append(city).append("\n").append(region).
                append("\n").append(country).append("\n").
                append(index).toString();
    }

    public String formatFullAddress(){
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
