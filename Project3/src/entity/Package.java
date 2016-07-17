
package entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "package", propOrder = {

})
public class Package {

    @XmlElement(required = true)
    private List<Dosage> dosages;

    @XmlAttribute(name = "count", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int count;

    @XmlAttribute(name = "measureUnit")
    private MeasureUnit measureUnit;

    @XmlAttribute(name = "price", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int price;

    @XmlAttribute(name = "packType", required = true)
    private PackageType packType;

    @XmlAttribute(name = "representationType", required = true)
    private RepresentationType representationType;

    /**
     * Gets the value of the dosages property.
     *
     * @return possible object is
     */
    public List<Dosage> getDosages() {
        return dosages;
    }

    /**
     * Sets the value of the dosages property.
     *
     * @param value allowed object is
     */
    public void setDosages(List<Dosage> value) {
        this.dosages = value;
    }

    /**
     * Gets the value of the count property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
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
     * {@link BigInteger }
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
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
    public PackageType getPackType() {
        return packType;
    }

    /**
     * Sets the value of the packType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPackType(PackageType value) {
        this.packType = value;
    }

    /**
     * Gets the value of the representationType property.
     *
     * @return possible object is
     * {@link String }
     */
    public RepresentationType getRepresentationType() {
        return representationType;
    }

    /**
     * Sets the value of the representationType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRepresentationType(RepresentationType value) {
        this.representationType = value;
    }
}