package parser.streamMarshaller;

import entity.Medicine;
import entity.Medicines;
import entity.TagName;
import parser.AnaloguesBinder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class MedicinesParser extends AbstractTagParser<Medicines> {

    public MedicinesParser(TagName tagName) {
        super(tagName);
        functionalContext.addInsertDataFunction(TagName.MEDICINE, medicine -> element.getMedicine().add((Medicine) medicine));
    }

    @Override
    protected void startElementParsing(Map<String, String> attributes) {
        element = new Medicines();
    }

    @Override
    public Medicines getParsingResult() {
        Map<Medicine, List<String>> analogues = new HashMap<>();
        for (Medicine med : element.getMedicine()) {
            analogues.put(med, med.getAnalogues().stream().map(Medicine::getID).collect(Collectors.toList()));
        }
        AnaloguesBinder.bindAnalogues(analogues, element.getMedicine());
        return element;
    }
}
