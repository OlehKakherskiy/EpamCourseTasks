
package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for medicine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="medicine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pharm" type="{}notEmptyString"/>
 *         &lt;element name="group" type="{}medicineGroup"/>
 *         &lt;element name="analogues" type="{}analogue" minOccurs="0"/>
 *         &lt;element name="versions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="version" type="{}version"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{}notEmptyString" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medicine", propOrder = {
    "pharm",
    "group",
    "analogues",
    "versions"
})
public class Medicine {

    @XmlElement(required = true)
    private String pharm;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private MedicineGroup group;

    private Analogue analogues;

    @XmlElement(required = true)
    private Medicine.Versions versions;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;

    /**
     * Gets the value of the pharm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharm() {
        return pharm;
    }

    /**
     * Sets the value of the pharm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharm(String value) {
        this.pharm = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link MedicineGroup }
     *     
     */
    public MedicineGroup getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedicineGroup }
     *     
     */
    public void setGroup(MedicineGroup value) {
        this.group = value;
    }

    /**
     * Gets the value of the analogues property.
     * 
     * @return
     *     possible object is
     *     {@link Analogue }
     *     
     */
    public Analogue getAnalogues() {
        return analogues;
    }

    /**
     * Sets the value of the analogues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Analogue }
     *     
     */
    public void setAnalogues(Analogue value) {
        this.analogues = value;
    }

    /**
     * Gets the value of the versions property.
     * 
     * @return
     *     possible object is
     *     {@link Medicine.Versions }
     *     
     */
    public Medicine.Versions getVersions() {
        return versions;
    }

    /**
     * Sets the value of the versions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Medicine.Versions }
     *     
     */
    public void setVersions(Medicine.Versions value) {
        this.versions = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
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
     *       &lt;all>
     *         &lt;element name="version" type="{}version"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Versions {

        @XmlElement(required = true)
        private Version version;

        /**
         * Gets the value of the version property.
         * 
         * @return
         *     possible object is
         *     {@link Version }
         *     
         */
        public Version getVersion() {
            return version;
        }

        /**
         * Sets the value of the version property.
         * 
         * @param value
         *     allowed object is
         *     {@link Version }
         *     
         */
        public void setVersion(Version value) {
            this.version = value;
        }

    }

}
