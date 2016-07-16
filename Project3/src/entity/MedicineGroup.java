
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for medicineGroup.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="medicineGroup">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="antibiotics"/>
 *     &lt;enumeration value="vitamins"/>
 *     &lt;enumeration value="antiviral"/>
 *     &lt;enumeration value="antiinflammatory"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "medicineGroup")
@XmlEnum
public enum MedicineGroup {

    @XmlEnumValue("antibiotics")
    ANTIBIOTICS("antibiotics"),

    @XmlEnumValue("vitamins")
    VITAMINS("vitamins"),

    @XmlEnumValue("antiviral")
    ANTIVIRAL("antiviral"),

    @XmlEnumValue("antiinflammatory")
    ANTIINFLAMMATORY("antiinflammatory");

    private final String value;

    MedicineGroup(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MedicineGroup fromValue(String v) {
        for (MedicineGroup c: MedicineGroup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
