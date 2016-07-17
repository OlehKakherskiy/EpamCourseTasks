package parser.domMarshalling;

import entity.*;
import entity.Package;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import java.util.List;
import java.util.function.Function;


/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DefaultDomSaver implements DomSaver {

    private Document resultDocument;

    @Override
    public Document save(Medicines medicines, Schema schema) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        try {
            resultDocument = dbFactory.newDocumentBuilder().newDocument(); //TODO: выставить схемы и тд
            Element root = createElement(TagName.MEDICINES);
            addSchemaAttributes(root);
            resultDocument.appendChild(root);

            medicines.getMedicine().stream().map(this::processMedicine).forEach(root::appendChild);

            return resultDocument;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addSchemaAttributes(Element root) {
        Attr attr1 = resultDocument.createAttribute("xmlns:xsi");
        attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");

        Attr attr2 = resultDocument.createAttribute("xmlns");
        attr2.setValue("http://www.w3schools.com");

        Attr attr3 = resultDocument.createAttribute("xsi:schemaLocation");
        attr3.setValue("http://www.w3schools.com medicine.xsd");

        root.setAttributeNodeNS(attr1);
        root.setAttributeNodeNS(attr2);
        root.setAttributeNodeNS(attr3);
    }

    private Node processMedicine(Medicine medicine) {
        Element result = createElement(TagName.MEDICINE);
        addAttribute(result, TagName.MEDICINE_NAME, medicine.getName());

        addAttribute(result, TagName.ID, medicine.getID()); //TODO: так нужно во всех ID проставить
        result.setIdAttributeNode(result.getAttributeNode(TagName.ID.getString()), true);

        processLeafNode(result, TagName.PHARM, medicine.getPharm());

        processLeafNode(result, TagName.GROUP, medicine.getGroup().value());

        if (medicine.getAnalogues().size() >= 1) {
            result.appendChild(processAnalogues(medicine.getAnalogues()));
        }
        result.appendChild(processProducers(medicine.getProducers()));
        return result;
    }

    private Node processAnalogues(List<Medicine> analogues) {
        return processAggregatedNode(TagName.ANALOGUES, analogues, this::processAnalogue);
    }

    private Node processAnalogue(Medicine analogue) {
        Element analog = createElement(TagName.ANALOGUE_ID);
        analog.setTextContent(analogue.getID());
        return analog;
    }

    private Node processProducers(List<Manufacturer> producers) {
        return processAggregatedNode(TagName.PRODUCERS, producers, this::processProducer);
    }

    private Node processProducer(Manufacturer producer) {
        Element prod = createElement(TagName.PRODUCER);
        addAttribute(prod, TagName.PRODUCER_NAME, producer.getName());

        prod.appendChild(processCertificate(producer.getCertificate()));
        prod.appendChild(processPackages(producer.getPackages()));

        return prod;
    }

    private Node processCertificate(Certificate certificate) {
        Element certNode = createElement(TagName.CERTIFICATE);

        addAttribute(certNode, TagName.CERTIFICATE_ID, certificate.getCertificateID());
        certNode.setIdAttributeNode(certNode.getAttributeNode(TagName.CERTIFICATE_ID.getString()), true);

        processLeafNode(certNode, TagName.START_DATE, certificate.getStartDate().toString());
        processLeafNode(certNode, TagName.END_DATE, certificate.getEndDate().toString());
        processLeafNode(certNode, TagName.REGISTERING_ORGANISATION, certificate.getRegisteringOrganisation());

        return certNode;
    }

    private Node processPackages(List<Package> packages) {
        return processAggregatedNode(TagName.PACKAGES, packages, this::processPackage);
    }

    private Node processPackage(Package pack) {
        Element packNode = createElement(TagName.PACKAGE);

        packNode.appendChild(processDosages(pack.getDosages()));

        addAttribute(packNode, TagName.COUNT, "" + pack.getCount());
        addAttribute(packNode, TagName.MEASURE_UNIT, pack.getMeasureUnit().value());
        addAttribute(packNode, TagName.PRICE, "" + pack.getPrice());
        addAttribute(packNode, TagName.PACK_TYPE, pack.getPackType().value());
        addAttribute(packNode, TagName.REPRESENTATION_TYPE, pack.getRepresentationType().value());

        return packNode;
    }

    private Node processDosages(List<Dosage> dosages) {
        return processAggregatedNode(TagName.DOSAGES, dosages, this::processDosage);
    }

    private Node processDosage(Dosage dosage) {
        Element d = createElement(TagName.DOSAGE);
        addAttribute(d, TagName.DOSAGE_COUNT, "" + dosage.getDosageCount());
        addAttribute(d, TagName.TIMES, "" + dosage.getTimes());
        addAttribute(d, TagName.PER, dosage.getPer().value());
        addAttribute(d, TagName.FOR, dosage.getFor().value());
        addAttribute(d, TagName.DOSAGE_MEASURE_UNIT, dosage.getMeasureUnit().value());
        return d;
    }

    private <E> Node processAggregatedNode(TagName resultNode, List<E> childNodes, Function<E, Node> processFunction) {
        Element result = createElement(resultNode);
        childNodes.stream().map(processFunction).forEach(result::appendChild);
        return result;
    }

    private Node processLeafNode(Node parentNode, TagName nodeName, String text) {
        Element result = createElement(nodeName);
        result.setTextContent(text);
        parentNode.appendChild(result);
        return result;
    }

    private Element createElement(TagName name) {
        return resultDocument.createElement(name.getString());
    }

    private void addAttribute(Element node, TagName name, String value) {
        node.setAttribute(name.getString(), value);
    }
}