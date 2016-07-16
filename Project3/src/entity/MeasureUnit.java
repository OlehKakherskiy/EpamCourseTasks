
package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for measureUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="measureUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="gram"/>
 *     &lt;enumeration value="milliliter"/>
 *     &lt;enumeration value="thing"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "measureUnit")
@XmlEnum
public enum MeasureUnit {

    @XmlEnumValue("gram")
    GRAM("gram"),

    @XmlEnumValue("milliliter")
    MILLILITER("milliliter"),

    @XmlEnumValue("thing")
    THING("thing");
    private final String value;

    MeasureUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasureUnit fromValue(String v) {
        for (MeasureUnit c: MeasureUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
