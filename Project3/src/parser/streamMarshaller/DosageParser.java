package parser.streamMarshaller;

import entity.*;

import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class DosageParser extends AbstractTagParser<Dosage> {

    public DosageParser(TagName tagName) {
        super(tagName);
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Dosage();
        element.setMeasureUnit(MeasureUnit.fromValue(getAttributeValue(attributes, TagName.MEASURE_UNIT)));
        element.setDosageCount(Integer.valueOf(getAttributeValue(attributes, TagName.DOSAGE_COUNT)));
        element.setFor(PeopleGroup.fromValue((getAttributeValue(attributes, TagName.FOR))));
        element.setPer(DosagePeriod.fromValue(getAttributeValue(attributes, TagName.PER)));
        element.setTimes(Integer.valueOf(getAttributeValue(attributes, TagName.TIMES)));
    }
}