package parser.domMarshalling;

import entity.*;
import entity.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.TagName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DefaultDomParser implements DomParser {

    @Override
    public Medicines parse(Document document) {
        Medicines medicines = new Medicines();
        medicines.getMedicine().addAll(getFirstDescendantsByName(document, TagName.MEDICINE.toString()).
                map(this::parseMedicine).collect(Collectors.toList()));
        return medicines;
    }

    private Medicine parseMedicine(Node node) {
        Medicine result = new Medicine();
        result.setGroup(MedicineGroup.valueOf(getAttributeValue(node, TagName.GROUP.getString())));
        result.setID(getAttributeValue(node, TagName.ID.getString()));
        result.setName(getAttributeValue(node, TagName.NAME.getString()));
        result.setPharm(getAttributeValue(node, TagName.PHARM.getString()));
        getFirstDescendantsByName(node, TagName.ANALOGUES.getString()).map(this::parseAnalogues).forEach(result::setAnalogues);
        getFirstDescendantsByName(node, TagName.VERSIONS.getString()).map(this::parseVersion).forEach(result::setVersions);
        return result;
    }

    private Analogue parseAnalogues(Node node) {
        Analogue result = new Analogue();
//        result.getAnalogueID().addAll(getFirstDescendantsByName(node, TagName.ANALOGUE_ID.getString()).map(n -> new String(n.getNodeValue())));
        return result; //TODO: разобраться с ID
    }

    private Version parseVersion(Node node) {
        Version res = new Version();
        Version.RepresentationTypes representationTypes = new Version.RepresentationTypes();
        res.setRepresentationTypes(representationTypes);

        representationTypes.getType().addAll(getFirstDescendantsByName(node, TagName.TYPE.getString()).
                map(node1 -> RepresentationType.valueOf(node1.getNodeName())).collect(Collectors.toList()));

        res.getDosage().addAll(getFirstDescendantsByName(node, TagName.DOSAGE.getString())
                .map(this::parseDosage).collect(Collectors.toList()));

        res.setCertificate(parseCertificate(getDescendantByName(node, TagName.CERTIFICATE.getString())));

        res.setPackage(parsePackage(getDescendantByName(node, TagName.PACKAGE.getString())));

        return res;
    }

    private Dosage parseDosage(Node node) {
        Dosage result = new Dosage();
        result.setDosageCount(BigInteger.valueOf(Integer.parseInt(getAttributeValue(node, TagName.DOSAGE_COUNT.getString()))));
        result.setDosageMeasureUnit(MeasureUnit.valueOf(getAttributeValue(node, TagName.MEASURE_UNIT.getString())));
        result.setFor(PeopleGroup.valueOf(getAttributeValue(node, TagName.FOR.getString())));
//        result.setPer() TODO: setPer должен принимать dosagePeriod
        result.setTimes(BigInteger.valueOf(Integer.parseInt(getAttributeValue(node, TagName.TIMES.getString()))));
        return result;
    }

    private Certificate parseCertificate(Node node) {
        Certificate res = new Certificate();
        res.setCertificateID(getAttributeValue(node, TagName.CERTIFICATE_ID.getString()));
        res.setRegisteringOrganisation(getAttributeValue(node, TagName.REGISTERING_ORGANISATION.getString()));
        //TODO:startDate, endDate
        return res;
    }

    private Package parsePackage(Node node) {
        Package pack = new Package();
        pack.get
    }

    private List<Node> toList(NodeList nodeList) {
        List<Node> res = new ArrayList<>(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            res.add(nodeList.item(i));
        }
        return res;
    }

    private String getAttributeValue(Node node, String attrName) {
        return node.getAttributes().getNamedItem(attrName).getNodeValue();
    }

    private Stream<Node> getFirstDescendantsByName(Node node, String nodeName) {
        return toList(node.getChildNodes()).stream().filter(n -> n.getNodeName().equals(nodeName));
    }

    private Node getDescendantByName(Node node, String nodeName) {
        return getFirstDescendantsByName(node, nodeName).findFirst().get();
    }
}
