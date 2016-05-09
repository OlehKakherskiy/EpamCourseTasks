package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ContactName {

    private String name;

    private String secondName;

    private String surname;

    public ContactName(String name, String secondName, String surname) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
    }

    public ContactName() {
    }

    public String getName() {
        return name;
    }

    public String formatShortName() {
        return new StringBuilder(surname).append(" ").append(name.charAt(0)).append(" ").
                append(secondName.charAt(0)).toString();
    }

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
