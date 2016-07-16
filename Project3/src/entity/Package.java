
package entity;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for package complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="package">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="measureUnit" type="{}measureUnit" default="gram" />
 *       &lt;attribute name="price" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="packType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="packType1"/>
 *             &lt;enumeration value="packType2"/>
 *             &lt;enumeration value="packType3"/>
 *             &lt;enumeration value="packType4"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="representationType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="drops"/>
 *             &lt;enumeration value="pills"/>
 *             &lt;enumeration value="capsules"/>
 *             &lt;enumeration value="powder"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "package")
public class Package {

    @XmlAttribute(name = "count", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int count;
    @XmlAttribute(name = "measureUnit")
    private MeasureUnit measureUnit;
    @XmlAttribute(name = "price", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int price;
    @XmlAttribute(name = "packType", required = true)
    private String packType;
    @XmlAttribute(name = "representationType", required = true)
    private String representationType;

    /**
     * Gets the value of the count property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the measureUnit property.
     *
     * @return possible object is
     * {@link MeasureUnit }
     */
    public MeasureUnit getMeasureUnit() {
        if (measureUnit == null) {
            return MeasureUnit.GRAM;
        } else {
            return measureUnit;
        }
    }

    /**
     * Sets the value of the measureUnit property.
     *
     * @param value allowed object is
     *              {@link MeasureUnit }
     */
    public void setMeasureUnit(MeasureUnit value) {
        this.measureUnit = value;
    }

    /**
     * Gets the value of the price property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setPrice(int value) {
        this.price = value;
    }

    /**
     * Gets the value of the packType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPackType() {
        return packType;
    }

    /**
     * Sets the value of the packType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPackType(String value) {
        this.packType = value;
    }

    /**
     * Gets the value of the representationType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRepresentationType() {
        return representationType;
    }

    /**
     * Sets the value of the representationType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRepresentationType(String value) {
        this.representationType = value;
    }

}
