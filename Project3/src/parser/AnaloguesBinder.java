package parser;

import entity.Medicine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AnaloguesBinder {

    private static Map<Medicine, List<String>> analogues;

    private static List<Medicine> elements;

    public static void bindAnalogues(Map<Medicine, List<String>> analogues, List<Medicine> elements) {
        AnaloguesBinder.analogues = analogues;
        AnaloguesBinder.elements = elements;

        analogues.entrySet().stream().forEach(AnaloguesBinder::bindAnalogue);
    }

    private static void bindAnalogue(Map.Entry<Medicine, List<String>> elementAnalogues) {
        elementAnalogues.getKey().setAnalogues(elementAnalogues.getValue().stream().
                map(AnaloguesBinder::findAnalogue).collect(Collectors.toList()));
    }

    private static Medicine findAnalogue(String id) {
        return elements.stream().filter(medicine -> medicine.getID().equals(id)).findFirst().orElse(null);
    }
}
