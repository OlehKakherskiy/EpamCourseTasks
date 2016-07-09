
package entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for package complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="package">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="count">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="cnt" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                 &lt;attribute name="measureUnit" type="{}measureUnit" default="gram" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="price" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
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
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "package", propOrder = {

})
public class Package {

    @XmlElement(required = true)
    protected Package.Count count;
    @XmlAttribute(name = "price", required = true)
    protected int price;
    @XmlAttribute(name = "packType", required = true)
    protected String packType;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Package.Count }
     *     
     */
    public Package.Count getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Package.Count }
     *     
     */
    public void setCount(Package.Count value) {
        this.count = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

    /**
     * Gets the value of the packType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackType() {
        return packType;
    }

    /**
     * Sets the value of the packType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackType(String value) {
        this.packType = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="cnt" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *       &lt;attribute name="measureUnit" type="{}measureUnit" default="gram" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Count {

        @XmlAttribute(name = "cnt", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger cnt;
        @XmlAttribute(name = "measureUnit")
        protected MeasureUnit measureUnit;

        /**
         * Gets the value of the cnt property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCnt() {
            return cnt;
        }

        /**
         * Sets the value of the cnt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCnt(BigInteger value) {
            this.cnt = value;
        }

        /**
         * Gets the value of the measureUnit property.
         * 
         * @return
         *     possible object is
         *     {@link MeasureUnit }
         *     
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
         * @param value
         *     allowed object is
         *     {@link MeasureUnit }
         *     
         */
        public void setMeasureUnit(MeasureUnit value) {
            this.measureUnit = value;
        }

    }

}
