
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "peopleGroup")
@XmlEnum
public enum PeopleGroup {

    @XmlEnumValue("adult")
    ADULT("adult"),

    @XmlEnumValue("adult men")
    ADULT_MEN("adult men"),

    @XmlEnumValue("adult women")
    ADULT_WOMEN("adult women"),

    @XmlEnumValue("children")
    CHILDREN("children"),

    @XmlEnumValue("pregnant")
    PREGNANT("pregnant");

    private final String value;

    PeopleGroup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PeopleGroup fromValue(String v) {
        for (PeopleGroup c: PeopleGroup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
