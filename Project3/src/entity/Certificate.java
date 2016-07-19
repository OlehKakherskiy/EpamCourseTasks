
package entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certificate", propOrder = {
})
public class Certificate {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private LocalDate startDate;

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private LocalDate endDate;

    @XmlElement(required = true)
    private String registeringOrganisation;

    @XmlAttribute(name = "certificateID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String certificateID;

    /**
     * Gets the value of the startDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setStartDate(LocalDate value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setEndDate(LocalDate value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the registeringOrganisation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegisteringOrganisation() {
        return registeringOrganisation;
    }

    /**
     * Sets the value of the registeringOrganisation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegisteringOrganisation(String value) {
        this.registeringOrganisation = value;
    }

    /**
     * Gets the value of the certificateID property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCertificateID() {
        return certificateID;
    }

    /**
     * Sets the value of the certificateID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCertificateID(String value) {
        this.certificateID = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;

        Certificate that = (Certificate) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!registeringOrganisation.equals(that.registeringOrganisation)) return false;
        return certificateID.equals(that.certificateID);

    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + registeringOrganisation.hashCode();
        result = 31 * result + certificateID.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", registeringOrganisation='" + registeringOrganisation + '\'' +
                ", certificateID='" + certificateID + '\'' +
                '}';
    }
}