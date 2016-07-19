package parser.domMarshalling;

import entity.*;
import entity.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.AnaloguesBinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DefaultDomParser implements DomParser {

    private Map<Medicine, List<String>> analogues = new HashMap<>();

    @Override
    public Medicines parse(Document document) {
        document.getDocumentElement().normalize();
        Medicines medicines = new Medicines();
        Node medicinesNode = document.getDocumentElement();
        medicines.getMedicine().addAll(getFirstLevelDescendantsByName(medicinesNode, TagName.MEDICINE).
                map(this::parseMedicine).collect(Collectors.toList()));
        AnaloguesBinder.bindAnalogues(analogues, medicines.getMedicine());
        return medicines;
    }

    private Medicine parseMedicine(Node node) {
        System.out.println(node.getNodeName());
        Medicine result = new Medicine();

        result.setID(getAttributeValue(node, TagName.ID));
        result.setName(getAttributeValue(node, TagName.MEDICINE_NAME));

        result.setGroup(MedicineGroup.fromValue(getFirstDescendantByName(node, TagName.GROUP).getTextContent()));
        result.setPharm(getFirstDescendantByName(node, TagName.PHARM).getTextContent());
        parseAnalogues(getFirstDescendantByName(node, TagName.ANALOGUES), result);

        result.setProducers((getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.PRODUCERS),
                TagName.PRODUCER).map(this::parseProducer).collect(Collectors.toList())));

        return result;
    }

    private void parseAnalogues(Node node, Medicine currentElement) {
        if (node == null)
            return;
        System.out.println(node.getNodeName()); //TODO: может не быть аналогов
        analogues.put(currentElement,
                getFirstLevelDescendantsByName(node, TagName.ANALOGUE_ID).map(Node::getTextContent).
                        collect(Collectors.toList()));
    }

    private Manufacturer parseProducer(Node node) {
        System.out.println(node.getNodeName());
        Manufacturer res = new Manufacturer();
        res.setName(getAttributeValue(node, TagName.PRODUCER_NAME));
        res.setCertificate(parseCertificate(getFirstDescendantByName(node, TagName.CERTIFICATE)));
        res.setPackages(getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.PACKAGES), TagName.PACKAGE).
                map(this::parsePackage).collect(Collectors.toList()));

        return res;
    }

    private Certificate parseCertificate(Node node) {
        System.out.println(node.getNodeName());
        Certificate res = new Certificate();
        res.setCertificateID(getAttributeValue(node, TagName.CERTIFICATE_ID));
        res.setRegisteringOrganisation(getFirstDescendantByName(node, TagName.REGISTERING_ORGANISATION).getTextContent());
        res.setStartDate(parseDate(node, TagName.START_DATE));
        res.setEndDate(parseDate(node, TagName.END_DATE));
        return res;
    }

    private Package parsePackage(Node node) {
        System.out.println(node.getNodeName());
        Package pack = new Package();
        pack.setCount(Integer.valueOf(getAttributeValue(node, TagName.COUNT)));
        pack.setPackType(PackageType.fromValue(getAttributeValue(node, TagName.PACK_TYPE)));
        pack.setPrice(Integer.valueOf(getAttributeValue(node, TagName.PRICE)));
        pack.setDosages(getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.DOSAGES),
                TagName.DOSAGE).map(this::parseDosage).collect(Collectors.toList()));
        pack.setRepresentationType(RepresentationType.fromValue(getAttributeValue(node, TagName.REPRESENTATION_TYPE)));
        pack.setMeasureUnit(MeasureUnit.fromValue(getAttributeValue(node, TagName.MEASURE_UNIT)));
        return pack;
    }

    private Dosage parseDosage(Node node) {
        System.out.println(node.getNodeName());
        Dosage result = new Dosage();
        result.setDosageCount(Integer.parseInt(getAttributeValue(node, TagName.DOSAGE_COUNT)));
        result.setMeasureUnit(MeasureUnit.fromValue(getAttributeValue(node, TagName.MEASURE_UNIT)));
        result.setFor(PeopleGroup.fromValue(getAttributeValue(node, TagName.FOR)));
        result.setPer(DosagePeriod.fromValue(getAttributeValue(node, TagName.PER)));
        result.setTimes(Integer.parseInt(getAttributeValue(node, TagName.TIMES)));
        return result;
    }


    private LocalDate parseDate(Node parentNode, TagName tagName) {
        System.out.println(parentNode.getNodeName());
        return LocalDate.parse(getFirstDescendantByName(parentNode, tagName).getTextContent());
    }


    private String getAttributeValue(Node node, TagName attrName) {
        return node.getAttributes().getNamedItem(attrName.getString()).getNodeValue();
    }

    private Stream<Node> getFirstLevelDescendantsByName(Node node, TagName nodeName) {
        NodeList nodeList = node.getChildNodes();
        System.out.println(nodeList.getLength());
        List<Node> res = new ArrayList<>(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals(nodeName.getString()))
                res.add(nodeList.item(i));
        }

        return res.stream();
    }

    private Node getFirstDescendantByName(Node node, TagName nodeName) {
        return getFirstLevelDescendantsByName(node, nodeName).findFirst().orElse(null);
    }
}