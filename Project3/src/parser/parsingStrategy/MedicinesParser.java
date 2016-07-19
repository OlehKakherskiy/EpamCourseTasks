package parser.parsingStrategy;

import entity.Medicine;
import entity.Medicines;
import entity.TagName;
import parser.AnaloguesBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class MedicinesParser extends AbstractTagParser<Medicines> {

    public MedicinesParser() {
        super(TagName.MEDICINES);
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
            List<Medicine> analogStubs = med.getAnalogues();
            med.setAnalogues(new ArrayList<>());
            ArrayList<String> ids = new ArrayList<>();
            analogStubs.forEach(el -> ids.add(el.getID()));
            analogues.put(med, ids);
        }
        AnaloguesBinder.bindAnalogues(analogues, element.getMedicine());
        return element;
    }
}
