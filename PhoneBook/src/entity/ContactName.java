package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ContactName {

    /**
     * contact name
     */
    private String name;

    /**
     * contact second name
     */
    private String secondName;

    /**
     * contact surname
     */
    private String surname;

    /**
     * Constructor that inits all params of full name
     *
     * @param name       contact name
     * @param secondName contact second name
     * @param surname    contact surname
     */
    public ContactName(String name, String secondName, String surname) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
    }

    /**
     * default constructor
     */
    public ContactName() {
    }

    public String getName() {
        return name;
    }

    /**
     * Formats string representation of full name in format :
     * surname name_first_letter secondName_first_letter
     *
     * @return short name
     */
    public String formatShortName() {
        return new StringBuilder(surname).append(" ").append(name.charAt(0)).append(" ").
                append(secondName.charAt(0)).toString();
    }

    /**
     * Formats string representation of full name in format:
     * surname name secondName
     *
     * @return full name
     */
    public String formatFullName() {
        return new StringBuilder(surname).append(" ").append(name).append(" ").
                append(secondName).toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
