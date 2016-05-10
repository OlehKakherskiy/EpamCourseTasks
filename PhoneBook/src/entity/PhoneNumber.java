package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class PhoneNumber {

    private String phoneNumber;

    private PhoneType type;

    public PhoneNumber(String phoneNumber, PhoneType type) {
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PhoneNumber:" + phoneNumber + ", Type:" + type;
    }
}
