package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
@XmlType(name = "dosagePeriod")
@XmlEnum
public enum DosagePeriod {

    @XmlEnumValue("30m")
    M_30("30m"),

    @XmlEnumValue("hour")
    HOUR("hour"),

    @XmlEnumValue("day")
    DAY("day"),

    @XmlEnumValue("week")
    WEEK("week"),

    @XmlEnumValue("month")
    MONTH("month");

    private final String value;

    DosagePeriod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DosagePeriod fromValue(String v) {
        for (DosagePeriod c: DosagePeriod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
