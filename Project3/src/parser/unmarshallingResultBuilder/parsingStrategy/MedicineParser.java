package parser.unmarshallingResultBuilder.parsingStrategy;

import entity.Manufacturer;
import entity.Medicine;
import entity.MedicineGroup;
import entity.TagName;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class MedicineParser extends AbstractTagParser<Medicine> {

    public MedicineParser() {
        super(TagName.MEDICINE);
        functionalContext.addInsertDataFunction(TagName.PHARM, value -> element.setPharm((String) value));
        functionalContext.addInsertDataFunction(TagName.GROUP, value -> setGroup((String) value));
        functionalContext.addInitFunction(TagName.ANALOGUES, this::initAnalogues);
        functionalContext.addInsertDataFunction(TagName.ANALOGUE_ID, this::addAnalogueMock);
        functionalContext.addInitFunction(TagName.PRODUCERS, this::initProducers);
        functionalContext.addInsertDataFunction(TagName.PRODUCER, producer -> element.getProducers().add((Manufacturer) producer));
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Medicine();
        element.setID(getAttributeValue(attributes, TagName.ID));
        element.setName(getAttributeValue(attributes, TagName.MEDICINE_NAME));
    }

    private void initAnalogues(Map<String, String> attributes) {
        element.setAnalogues(new ArrayList<>());
    }

    private void initProducers(Map<String, String> attributes) {
        element.setProducers(new ArrayList<>());
    }

    private void setGroup(String group) {
        element.setGroup(MedicineGroup.fromValue(group));
    }

    private void addAnalogueMock(Object id) {
        Medicine mock = new Medicine();
        mock.setID((String) id);
        element.getAnalogues().add(mock);
    }
}