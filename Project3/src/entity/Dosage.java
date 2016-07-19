
package entity;

import javax.xml.bind.annotation.*;


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
     */
    public int getDosageCount() {
        return dosageCount;
    }

    /**
     * Sets the value of the dosageCount property.
     *
     */
    public void setDosageCount(int value) {
        this.dosageCount = value;
    }

    /**
     * Gets the value of the times property.
     *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dosage)) return false;

        Dosage dosage = (Dosage) o;

        if (dosageCount != dosage.dosageCount) return false;
        if (times != dosage.times) return false;
        if (per != dosage.per) return false;
        if (_for != dosage._for) return false;
        return measureUnit == dosage.measureUnit;

    }

    @Override
    public int hashCode() {
        int result = dosageCount;
        result = 31 * result + times;
        result = 31 * result + per.hashCode();
        result = 31 * result + _for.hashCode();
        result = 31 * result + measureUnit.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dosage{" +
                "dosageCount=" + dosageCount +
                ", times=" + times +
                ", per=" + per +
                ", _for=" + _for +
                ", measureUnit=" + measureUnit +
                '}';
    }
}