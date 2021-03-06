
package entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medicine", propOrder = {
        "pharm",
        "group",
        //"analogues", //TODO: ошибка, комментируем
        "producers"
})
public class Medicine {

    @XmlElement(required = true)
    private String pharm;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private MedicineGroup group;

    @XmlElementRef(name = "analogues", type = Medicine.class)
    private List<Medicine> analogues = new ArrayList<>();

    @XmlElement(required = true)
    private List<Manufacturer> producers = new ArrayList<>();

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
     * @return possible object is
     * {@link String }
     */
    public String getPharm() {
        return pharm;
    }

    /**
     * Sets the value of the pharm property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPharm(String value) {
        this.pharm = value;
    }

    /**
     * Gets the value of the group property.
     *
     * @return possible object is
     * {@link MedicineGroup }
     */
    public MedicineGroup getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     *
     * @param value allowed object is
     *              {@link MedicineGroup }
     */
    public void setGroup(MedicineGroup value) {
        this.group = value;
    }


    /**
     * Gets the value of the producers property.
     *
     * @return possible object is
     */
    public List<Manufacturer> getProducers() {
        return producers;
    }

    /**
     * Sets the value of the producers property.
     *
     * @param value allowed object is
     */
    public void setProducers(List<Manufacturer> value) {
        this.producers = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
    }

    public List<Medicine> getAnalogues() {
        return analogues;
    }

    public void setAnalogues(List<Medicine> analogues) {
        this.analogues = analogues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;

        Medicine medicine = (Medicine) o;

        if (!pharm.equals(medicine.pharm)) return false;
        if (group != medicine.group) return false;
        if (!analogues.equals(medicine.analogues)) return false;
        if (!producers.equals(medicine.producers)) return false;
        if (!name.equals(medicine.name)) return false;
        return id.equals(medicine.id);

    }

    @Override
    public int hashCode() {
        int result = pharm.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + analogues.hashCode();
        result = 31 * result + producers.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "pharm='" + pharm + '\'' +
                ", group=" + group +
                ", analogues=" + analogues +
                ", producers=" + producers +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
