
package entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the entity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnalogueAnalogueID_QNAME = new QName("", "analogueID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: entity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Version }
     * 
     */
    public Version createVersion() {
        return new Version();
    }

    /**
     * Create an instance of {@link Package }
     * 
     */
    public Package createPackage() {
        return new Package();
    }

    /**
     * Create an instance of {@link Medicines }
     * 
     */
    public Medicines createMedicines() {
        return new Medicines();
    }

    /**
     * Create an instance of {@link Medicine }
     * 
     */
    public Medicine createMedicine() {
        return new Medicine();
    }

    /**
     * Create an instance of {@link Dosage }
     * 
     */
    public Dosage createDosage() {
        return new Dosage();
    }

    /**
     * Create an instance of {@link Certificate }
     * 
     */
    public Certificate createCertificate() {
        return new Certificate();
    }

    /**
     * Create an instance of {@link Analogue }
     * 
     */
    public Analogue createAnalogue() {
        return new Analogue();
    }

    /**
     * Create an instance of {@link Version.RepresentationTypes }
     * 
     */
    public Version.RepresentationTypes createVersionRepresentationTypes() {
        return new Version.RepresentationTypes();
    }

    /**
     * Create an instance of {@link Package.Count }
     * 
     */
    public Package.Count createPackageCount() {
        return new Package.Count();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "analogueID", scope = Analogue.class)
    @XmlIDREF
    public JAXBElement<Object> createAnalogueAnalogueID(Object value) {
        return new JAXBElement<Object>(_AnalogueAnalogueID_QNAME, Object.class, Analogue.class, value);
    }

}
