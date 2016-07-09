
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for representationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="representationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="drops"/>
 *     &lt;enumeration value="pills"/>
 *     &lt;enumeration value="capsules"/>
 *     &lt;enumeration value="powder"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "representationType")
@XmlEnum
public enum RepresentationType {

    @XmlEnumValue("drops")
    DROPS("drops"),
    @XmlEnumValue("pills")
    PILLS("pills"),
    @XmlEnumValue("capsules")
    CAPSULES("capsules"),
    @XmlEnumValue("powder")
    POWDER("powder");
    private final String value;

    RepresentationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RepresentationType fromValue(String v) {
        for (RepresentationType c: RepresentationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
