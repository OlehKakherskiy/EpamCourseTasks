
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for peopleGroup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="peopleGroup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="adult"/>
 *     &lt;enumeration value="adult men"/>
 *     &lt;enumeration value="adult women"/>
 *     &lt;enumeration value="children"/>
 *     &lt;enumeration value="pregnant"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
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
