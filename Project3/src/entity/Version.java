
package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;all>
 *         &lt;element name="producers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="producer" type="{}manufacturer"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dosages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="dosage" type="{}dosage"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "version", propOrder = {

})
public class Version {

    @XmlElement(required = true)
    private Version.Producers producers;
    @XmlElement(required = true)
    private Version.Dosages dosages;

    /**
     * Gets the value of the producers property.
     * 
     * @return
     *     possible object is
     *     {@link Version.Producers }
     *     
     */
    public Version.Producers getProducers() {
        return producers;
    }

    /**
     * Sets the value of the producers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version.Producers }
     *     
     */
    public void setProducers(Version.Producers value) {
        this.producers = value;
    }

    /**
     * Gets the value of the dosages property.
     * 
     * @return
     *     possible object is
     *     {@link Version.Dosages }
     *     
     */
    public Version.Dosages getDosages() {
        return dosages;
    }

    /**
     * Sets the value of the dosages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version.Dosages }
     *     
     */
    public void setDosages(Version.Dosages value) {
        this.dosages = value;
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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="dosage" type="{}dosage"/>
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
        "dosage"
    })
    public static class Dosages {

        @XmlElement(required = true)
        private List<Dosage> dosage;

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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="producer" type="{}manufacturer"/>
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
        "producer"
    })
    public static class Producers {

        @XmlElement(required = true)
        private List<Manufacturer> producer;

        /**
         * Gets the value of the producer property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the producer property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProducer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Manufacturer }
         * 
         * 
         */
        public List<Manufacturer> getProducer() {
            if (producer == null) {
                producer = new ArrayList<Manufacturer>();
            }
            return this.producer;
        }

    }

}
