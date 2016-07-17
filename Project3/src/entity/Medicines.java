
package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "medicine"
})
@XmlRootElement(name = "medicines")
public class Medicines {

    private List<Medicine> medicine = new ArrayList<>();

    public List<Medicine> getMedicine() {
        return this.medicine;
    }

}
