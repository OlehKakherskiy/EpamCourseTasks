package parser.streamMarshaller;

import entity.*;
import entity.Package;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class PackageParser extends AbstractTagParser<Package> {

    public PackageParser() {
        super(TagName.PACKAGE);
        functionalContext.addInitFunction(TagName.DOSAGES, this::initDosages);
        functionalContext.addInsertDataFunction(TagName.DOSAGE, dosage -> element.getDosages().add((Dosage) dosage));
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Package();
        element.setMeasureUnit(MeasureUnit.fromValue(getAttributeValue(attributes, TagName.MEASURE_UNIT)));
        element.setRepresentationType(RepresentationType.fromValue(getAttributeValue(attributes, TagName.REPRESENTATION_TYPE)));
        element.setCount(Integer.valueOf(getAttributeValue(attributes, TagName.COUNT)));
        element.setPrice(Integer.valueOf(getAttributeValue(attributes, TagName.PRICE)));
        element.setPackType(PackageType.fromValue(getAttributeValue(attributes, TagName.PACK_TYPE)));
    }


    private void initDosages(Map<String, String> attributes) {
        element.setDosages(new ArrayList<>());
    }
}
