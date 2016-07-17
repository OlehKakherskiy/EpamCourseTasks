package parser.domMarshalling;

import entity.*;
import entity.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import entity.DosagePeriod;
import entity.PackageType;
import entity.RepresentationType;
import entity.TagName;

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

    private List<Medicine> medicines;

    @Override
    public Medicines parse(Document document) {
        document.getDocumentElement().normalize();
        Medicines medicines = new Medicines();
        Node medicinesNode = document.getDocumentElement();
        this.medicines = getFirstLevelDescendantsByName(medicinesNode, TagName.MEDICINE.getString()).
                map(this::parseMedicine).collect(Collectors.toList());
        medicines.getMedicine().addAll(this.medicines);
        bindAnalogues();
        return medicines;
    }

    private Medicine parseMedicine(Node node) {
        System.out.println(node.getNodeName());
        Medicine result = new Medicine();

        result.setID(getAttributeValue(node, TagName.ID.getString()));
        result.setName(getAttributeValue(node, TagName.MEDICINE_NAME.getString()));

        result.setGroup(MedicineGroup.fromValue(getFirstDescendantByName(node, TagName.GROUP.getString()).getTextContent()));
        result.setPharm(getFirstDescendantByName(node, TagName.PHARM.getString()).getTextContent());
        parseAnalogues(getFirstDescendantByName(node, TagName.ANALOGUES.getString()), result);

        result.setProducers((getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.PRODUCERS.getString()),
                TagName.PRODUCER.getString()).map(this::parseProducer).collect(Collectors.toList())));

        return result;
    }

    private void parseAnalogues(Node node, Medicine currentElement) {
        if (node == null)
            return;
        System.out.println(node.getNodeName()); //TODO: может не быть аналогов
        analogues.put(currentElement,
                getFirstLevelDescendantsByName(node, TagName.ANALOGUE_ID.getString()).map(Node::getTextContent).
                        collect(Collectors.toList()));
    }

    private Manufacturer parseProducer(Node node) {
        System.out.println(node.getNodeName());
        Manufacturer res = new Manufacturer();
        res.setName(getAttributeValue(node, TagName.PRODUCER_NAME.getString()));
        res.setCertificate(parseCertificate(getFirstDescendantByName(node, TagName.CERTIFICATE.getString())));
        res.setPackages(getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.PACKAGES.getString()), TagName.PACKAGE.getString()).
                map(this::parsePackage).collect(Collectors.toList()));

        return res;
    }

    private Certificate parseCertificate(Node node) {
        System.out.println(node.getNodeName());
        Certificate res = new Certificate();
        res.setCertificateID(getAttributeValue(node, TagName.CERTIFICATE_ID.getString()));
        res.setRegisteringOrganisation(getFirstDescendantByName(node, TagName.REGISTERING_ORGANISATION.getString()).getTextContent());
        res.setStartDate(parseDate(node, TagName.START_DATE.getString()));
        res.setEndDate(parseDate(node, TagName.END_DATE.getString()));
        return res;
    }

    private Package parsePackage(Node node) {
        System.out.println(node.getNodeName());
        Package pack = new Package();
        pack.setCount(Integer.valueOf(getAttributeValue(node, TagName.COUNT.getString())));
        pack.setPackType(PackageType.fromValue(getAttributeValue(node, TagName.PACK_TYPE.getString())));
        pack.setPrice(Integer.valueOf(getAttributeValue(node, TagName.PRICE.getString())));
        pack.setDosages(getFirstLevelDescendantsByName(getFirstDescendantByName(node, TagName.DOSAGES.getString()),
                TagName.DOSAGE.getString()).map(this::parseDosage).collect(Collectors.toList()));
        pack.setRepresentationType(RepresentationType.fromValue(getAttributeValue(node, TagName.REPRESENTATION_TYPE.getString())));
        return pack;
    }

    private Dosage parseDosage(Node node) {
        System.out.println(node.getNodeName());
        Dosage result = new Dosage();
        result.setDosageCount(Integer.parseInt(getAttributeValue(node, TagName.DOSAGE_COUNT.getString())));
        result.setMeasureUnit(MeasureUnit.fromValue(getAttributeValue(node, TagName.MEASURE_UNIT.getString())));
        result.setFor(PeopleGroup.fromValue(getAttributeValue(node, TagName.FOR.getString())));
        result.setPer(DosagePeriod.fromValue(getAttributeValue(node, TagName.PER.getString())));
        result.setTimes(Integer.parseInt(getAttributeValue(node, TagName.TIMES.getString())));
        return result;
    }


    private LocalDate parseDate(Node parentNode, String tagName) {
        System.out.println(parentNode.getNodeName());
        return LocalDate.parse(getFirstDescendantByName(parentNode, tagName).getTextContent());
    }

    private void bindAnalogues() {
        analogues.entrySet().stream().forEach(this::bindAnalogue);
    }

    private void bindAnalogue(Map.Entry<Medicine, List<String>> elementAnalogues) {
        elementAnalogues.getKey().setAnalogues(elementAnalogues.getValue().stream().
                map(this::findAnalogue).collect(Collectors.toList()));
    }

    private Medicine findAnalogue(String id) {
        return this.medicines.stream().filter(medicine -> medicine.getID().equals(id)).findFirst().orElse(null);
    }

    private String getAttributeValue(Node node, String attrName) {
        return node.getAttributes().getNamedItem(attrName).getNodeValue();
    }

    private Stream<Node> getFirstLevelDescendantsByName(Node node, String nodeName) {
        NodeList nodeList = node.getChildNodes();
        System.out.println(nodeList.getLength());
        List<Node> res = new ArrayList<>(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals(nodeName))
                res.add(nodeList.item(i));
        }

        return res.stream();
    }

    private Node getFirstDescendantByName(Node node, String nodeName) {
        return getFirstLevelDescendantsByName(node, nodeName).findFirst().orElse(null);
    }
}