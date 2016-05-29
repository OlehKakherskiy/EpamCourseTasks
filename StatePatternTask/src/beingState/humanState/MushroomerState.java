package beingState.humanState;


import beingState.DefinedState;

import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public class MushroomerState implements DefinedState {

    private String forestName;

    @Override
    public String processState() {
        return String.format("Forest name: %s.Searching mushrooms.", forestName);
    }

    @Override
    public void initState(Map<String, Object> stateAttributes) {
        forestName = (String) stateAttributes.get("forestName");
        forestName = forestName == null ? "no information" : forestName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MushroomerState)) return false;

        MushroomerState that = (MushroomerState) o;

        return forestName.equals(that.forestName);

    }

    @Override
    public int hashCode() {
        return forestName.hashCode();
    }
}