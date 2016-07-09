
package entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for version complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="version">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificate" type="{}certificate"/>
 *         &lt;element name="representationTypes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="type" type="{}representationType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="package" type="{}package"/>
 *         &lt;element name="dosage" type="{}dosage" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "version", propOrder = {
    "certificate",
    "representationTypes",
    "_package",
    "dosage"
})
public class Version {

    @XmlElement(required = true)
    protected Certificate certificate;
    @XmlElement(required = true)
    protected Version.RepresentationTypes representationTypes;
    @XmlElement(name = "package", required = true)
    protected Package _package;
    @XmlElement(required = true)
    protected List<Dosage> dosage;

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link Certificate }
     *     
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Certificate }
     *     
     */
    public void setCertificate(Certificate value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the representationTypes property.
     * 
     * @return
     *     possible object is
     *     {@link Version.RepresentationTypes }
     *     
     */
    public Version.RepresentationTypes getRepresentationTypes() {
        return representationTypes;
    }

    /**
     * Sets the value of the representationTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version.RepresentationTypes }
     *     
     */
    public void setRepresentationTypes(Version.RepresentationTypes value) {
        this.representationTypes = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setPackage(Package value) {
        this._package = value;
    }

    /**
     * Gets the value of the dosage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dosage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDosage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dosage }
     * 
     * 
     */
    public List<Dosage> getDosage() {
        if (dosage == null) {
            dosage = new ArrayList<Dosage>();
        }
        return this.dosage;
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
     *       &lt;sequence>
     *         &lt;element name="type" type="{}representationType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "type"
    })
    public static class RepresentationTypes {

        @XmlElement(required = true)
        @XmlSchemaType(name = "string")
        protected List<RepresentationType> type;

        /**
         * Gets the value of the type property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the type property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RepresentationType }
         * 
         * 
         */
        public List<RepresentationType> getType() {
            if (type == null) {
                type = new ArrayList<RepresentationType>();
            }
            return this.type;
        }

    }

}
