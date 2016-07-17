package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
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
        for (RepresentationType c : RepresentationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
