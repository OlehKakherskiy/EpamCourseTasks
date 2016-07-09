
package entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for dosage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dosage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="dosageCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="dosageMeasureUnit" type="{}measureUnit" default="gram" />
 *       &lt;attribute name="times" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" default="1" />
 *       &lt;attribute name="per" use="required" type="{}dosagePeriod" />
 *       &lt;attribute name="for" use="required" type="{}peopleGroup" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dosage")
public class Dosage {

    @XmlAttribute(name = "dosageCount", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dosageCount;
    @XmlAttribute(name = "dosageMeasureUnit")
    protected MeasureUnit dosageMeasureUnit;
    @XmlAttribute(name = "times")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger times;
    @XmlAttribute(name = "per", required = true)
    protected String per;
    @XmlAttribute(name = "for", required = true)
    protected PeopleGroup _for;

    /**
     * Gets the value of the dosageCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDosageCount() {
        return dosageCount;
    }

    /**
     * Sets the value of the dosageCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDosageCount(BigInteger value) {
        this.dosageCount = value;
    }

    /**
     * Gets the value of the dosageMeasureUnit property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureUnit }
     *     
     */
    public MeasureUnit getDosageMeasureUnit() {
        if (dosageMeasureUnit == null) {
            return MeasureUnit.GRAM;
        } else {
            return dosageMeasureUnit;
        }
    }

    /**
     * Sets the value of the dosageMeasureUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureUnit }
     *     
     */
    public void setDosageMeasureUnit(MeasureUnit value) {
        this.dosageMeasureUnit = value;
    }

    /**
     * Gets the value of the times property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimes() {
        if (times == null) {
            return new BigInteger("1");
        } else {
            return times;
        }
    }

    /**
     * Sets the value of the times property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimes(BigInteger value) {
        this.times = value;
    }

    /**
     * Gets the value of the per property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPer() {
        return per;
    }

    /**
     * Sets the value of the per property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPer(String value) {
        this.per = value;
    }

    /**
     * Gets the value of the for property.
     * 
     * @return
     *     possible object is
     *     {@link PeopleGroup }
     *     
     */
    public PeopleGroup getFor() {
        return _for;
    }

    /**
     * Sets the value of the for property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeopleGroup }
     *     
     */
    public void setFor(PeopleGroup value) {
        this._for = value;
    }

    //TODO: DOSAGE_PERIOD нету!!!!

}
