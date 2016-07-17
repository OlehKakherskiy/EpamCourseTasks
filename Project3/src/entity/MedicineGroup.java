
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


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
