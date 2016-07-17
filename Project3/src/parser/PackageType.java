package parser;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
@XmlType(name = "packType")
@XmlEnum
public enum PackageType {

    @XmlEnumValue("packType1")
    PACKAGE_TYPE_1("packType1"),

    @XmlEnumValue("packType2")
    PACKAGE_TYPE_2("packType2"),

    @XmlEnumValue("packType3")
    PACKAGE_TYPE_3("packType3"),

    @XmlEnumValue("packType4")
    PACKAGE_TYPE_4("packType4");

    private final String value;

    PackageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PackageType fromValue(String v) {
        for (PackageType c : PackageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
