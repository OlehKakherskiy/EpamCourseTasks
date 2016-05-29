package beingState.humanState;


import beingState.DefinedState;

import java.util.Map;

/**
 * Represents fisherman
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public class FishermanState implements DefinedState {

    private static final String stringTemplate = "Fishing in lake %s with fishing rod, which name is %s";

    private String fishingRod;

    private String lakeName;

    @Override
    public String processState() {
        return String.format(stringTemplate, lakeName, fishingRod).trim();
    }

    @Override
    public void initState(Map<String, Object> stateAttributes) {
        fishingRod = (String) stateAttributes.get("fishingRod");
        fishingRod = fishingRod == null ? "undefined" : fishingRod;
        lakeName = (String) stateAttributes.get("lakeName");
        lakeName = lakeName == null ? "undefined" : lakeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FishermanState)) return false;

        FishermanState that = (FishermanState) o;

        return fishingRod.equals(that.fishingRod) && lakeName.equals(that.lakeName);

    }

    @Override
    public int hashCode() {
        int result = fishingRod.hashCode();
        result = 31 * result + lakeName.hashCode();
        return result;
    }
}