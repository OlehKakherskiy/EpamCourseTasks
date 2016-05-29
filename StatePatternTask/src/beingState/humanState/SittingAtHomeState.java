package beingState.humanState;

import beingState.DefinedState;

import java.util.Map;

/**
 * Represent human, that have no special state.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class SittingAtHomeState implements DefinedState {

    @Override
    public String processState() {
        return "Sitting at home";
    }

    @Override
    public void initState(Map<String, Object> stateAttributes) {
        stateAttributes.containsKey("sittingAtHome");
    }
}
