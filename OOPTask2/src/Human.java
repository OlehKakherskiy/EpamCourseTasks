import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents human information, e.g. name, surname, sex, birth date, etc. Also every human can have additional
 * attributes, described in {@link HumanAttribute} type. For example, each person can have higher education, job,
 * be a hunter, have driver license, etc. These attributes can be added or removed dynamically, like in real life.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Human implements Cloneable {

    private String name;

    private String secondName;

    private String surname;

    private LocalDate birthDate;

    private Sex sex;

    private String address;

    private Set<HumanAttribute> additionalAttributes;

    public Human(String name, String secondName, String surname,
                 LocalDate birthDate, Sex sex, String address,
                 HumanAttribute... additionalAttributes) {
        CheckUtils.fullCheck(name, "human name");
        CheckUtils.fullCheck(secondName, "human second name");
        CheckUtils.fullCheck(surname, "human surname");
        CheckUtils.fullCheck(birthDate, "human birth date");
        CheckUtils.fullCheck(sex, "human sex");
        CheckUtils.fullCheck(address, "human living address");
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.additionalAttributes = new HashSet<>();
        this.additionalAttributes.addAll(Arrays.asList(additionalAttributes));
    }

    public boolean addAdditionalAttribute(HumanAttribute attribute) {
        CheckUtils.nullCheck(attribute, "human attribute");
        return additionalAttributes.add(attribute);
    }

    public boolean removeAdditionalAttribute(HumanAttribute attribute) {
        return additionalAttributes.remove(attribute);
    }

    public void setAddress(String address) {
        CheckUtils.fullCheck(address, "human living address");
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return LocalDate.of(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth());
    }

    public Sex getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public Set<HumanAttribute> getAdditionalAttributes() {
        return additionalAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;

        Human human = (Human) o;

        if (!name.equals(human.name)) return false;
        if (!secondName.equals(human.secondName)) return false;
        if (!surname.equals(human.surname)) return false;
        if (!birthDate.equals(human.birthDate)) return false;
        if (sex != human.sex) return false;
        if (!address.equals(human.address)) return false;
        return additionalAttributes.equals(human.additionalAttributes);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + additionalAttributes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", additionalAttributes=" + additionalAttributes +
                '}';
    }

    @Override
    public Human clone() throws CloneNotSupportedException {
        Human clone = new Human(name, secondName, surname, getBirthDate(), sex, new String(address));
        for (HumanAttribute attr : additionalAttributes) {
            clone.addAdditionalAttribute(attr.clone());
        }
        return clone;
    }
}
