
package entity;

import parser.DosagePeriod;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for dosage complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="dosage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="dosageCount" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="times" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" default="1" />
 *       &lt;attribute name="per" use="required" type="{}dosagePeriod" />
 *       &lt;attribute name="for" use="required" type="{}peopleGroup" />
 *       &lt;attribute name="measureUnit" use="required" type="{}measureUnit" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dosage")
public class Dosage {

    @XmlAttribute(name = "dosageCount", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int dosageCount;

    @XmlAttribute(name = "times")
    @XmlSchemaType(name = "positiveInteger")
    private int times;

    @XmlAttribute(name = "per", required = true)
    private DosagePeriod per;

    @XmlAttribute(name = "for", required = true)
    private PeopleGroup _for;

    @XmlAttribute(name = "measureUnit", required = true)
    private MeasureUnit measureUnit;

    /**
     * Gets the value of the dosageCount property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getDosageCount() {
        return dosageCount;
    }

    /**
     * Sets the value of the dosageCount property.
     *
     * @param value allowed object is
     *              {@link int }
     */
    public void setDosageCount(int value) {
        this.dosageCount = value;
    }

    /**
     * Gets the value of the times property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getTimes() {
        return times;
    }


    /**
     * Sets the value of the times property.
     *
     */
    public void setTimes(int value) {
        this.times = value;
    }

    /**
     * Gets the value of the per property.
     *
     * @return possible object is
     * {@link String }
     */
    public DosagePeriod getPer() {
        return per;
    }

    /**
     * Sets the value of the per property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPer(DosagePeriod value) {
        this.per = value;
    }

    /**
     * Gets the value of the for property.
     *
     * @return possible object is
     * {@link PeopleGroup }
     */
    public PeopleGroup getFor() {
        return _for;
    }

    /**
     * Sets the value of the for property.
     *
     * @param value allowed object is
     *              {@link PeopleGroup }
     */
    public void setFor(PeopleGroup value) {
        this._for = value;
    }

    /**
     * Gets the value of the measureUnit property.
     *
     * @return possible object is
     * {@link MeasureUnit }
     */
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
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

}