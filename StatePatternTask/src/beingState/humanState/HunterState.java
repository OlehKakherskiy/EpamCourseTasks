package beingState.humanState;


import beingState.DefinedState;

import java.util.Map;

/**
 * Represents hunter state
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public class HunterState implements DefinedState {

    private static final String template = "Looking at such animals, as %s. Have a gun. Name: %s, manufacturer: %s";

    private String gunName;

    private String gunManufacturer;

    private String animalName;

    @Override
    public String processState() {
        return String.format(template, animalName, gunName, gunManufacturer);
    }

    @Override
    public void initState(Map<String, Object> stateAttributes) {
        gunName = (String) stateAttributes.get("gunName");
        gunManufacturer = (String) stateAttributes.get("gunManufacturer");
        animalName = (String) stateAttributes.get("animalName");

        gunName = gunName == null ? "undefined" : gunName;
        gunManufacturer = gunManufacturer == null ? "undefined" : gunManufacturer;
        animalName = animalName == null ? "... oh, no. It was a mistake. But i'm hearing them" : animalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HunterState)) return false;

        HunterState that = (HunterState) o;

        return gunName.equals(that.gunName) &&
                gunManufacturer.equals(that.gunManufacturer) &&
                animalName.equals(that.animalName);

    }

    @Override
    public int hashCode() {
        int result = gunName.hashCode();
        result = 31 * result + gunManufacturer.hashCode();
        result = 31 * result + animalName.hashCode();
        return result;
    }
}