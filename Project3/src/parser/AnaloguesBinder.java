package parser;

import entity.Medicine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class, main purpose of which is bind medicine analogues according to their ID.
 * For instance, one medicine has idref to another (according to xml), so this element
 * has the id of another. This class takes all medicine object as parameter and their analogues
 * IDs and fill the field {@link Medicine#analogues}.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see Medicine
 */
public class AnaloguesBinder {

    /**
     * analogues mapping
     */
    private static Map<Medicine, List<String>> analogues;

    /**
     * all medicine's objects
     */
    private static List<Medicine> elements;

    /**
     * fills every {@link #elements} analogues with another {@link #elements} objects, getting binding
     * information from map parameter
     *
     * @param analogues element - list of analogues' ids map
     * @param elements  all medicines objects
     */
    public static void bindAnalogues(Map<Medicine, List<String>> analogues, List<Medicine> elements) {
        AnaloguesBinder.analogues = analogues;
        AnaloguesBinder.elements = elements;

        analogues.entrySet().stream().forEach(AnaloguesBinder::bindAnalogue);
    }

    /**
     * finds analogues of target element and bind them (adds a link of analogue to target medicine object, but not vice versa)
     *
     * @param elementAnalogues medicineObject - ids of all analogues
     */
    private static void bindAnalogue(Map.Entry<Medicine, List<String>> elementAnalogues) {
        elementAnalogues.getKey().setAnalogues(elementAnalogues.getValue().stream().
                map(AnaloguesBinder::findAnalogue).collect(Collectors.toList()));
    }

    /**
     * finds medicine object from {@link #elements} list according to its id.
     *
     * @param id {@link Medicine#id} of medicine object
     * @return medicine object with current id
     */
    private static Medicine findAnalogue(String id) {
        Medicine result = elements.stream().filter(medicine -> medicine.getID().equals(id)).findFirst().orElse(null);
        if (result == null) {
            throw new RuntimeException("XML is invalid - there's no medicine with id '" + id + "'");
        }
        return result;
    }
}