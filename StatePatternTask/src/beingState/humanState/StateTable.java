package beingState.humanState;

import beingState.CompositeState;
import beingState.DefinedState;
import beingState.State;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class encapsulates being's transitions between different states. This
 * transition is identified by attributes that each state need to have.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public class StateTable {

    /**
     * key-value pairs, where key - set of state attributes, value - state type
     * that compatible with these attributes
     */
    private Map<Set<String>, Class<? extends DefinedState>> stateTransitions;

    /**
     * returns the state compatible with all attributes in input parameter.
     * Omits parameters, that are not compatible with any of states. If there are
     * parts of parameters that compatible with one state, and others - with other -
     * returns {@link CompositeState} object
     *
     * @param stateAttributes actual state attributes
     * @throws RuntimeException if stateAttributes are incompatible with all
     *                          states in this transition table
     */
    public State changeState(Map<String, Object> stateAttributes) throws IllegalAccessException, InstantiationException {
        Set<DefinedState> states = new HashSet<>();
        for (String attKey : stateAttributes.keySet()) {
            for (Set<String> expectedKeys : stateTransitions.keySet()) {
                if (expectedKeys.contains(attKey)) {
                    DefinedState state = stateTransitions.get(expectedKeys).newInstance();
                    state.initState(stateAttributes);
                    states.add(state);
                }
            }
        }
        State result = null;
        if (states.size() == 0) {
            throw new RuntimeException("there is no state that matches to these attributes: " + stateAttributes.toString());
        }

        return (states.size() > 1) ? new CompositeState(states) : states.iterator().next();
    }

    Map<Set<String>, Class<? extends DefinedState>> getStateTransitions() {
        return stateTransitions;
    }

    void setStateTransitions(Map<Set<String>, Class<? extends DefinedState>> newVal) {
        stateTransitions = newVal;
    }
}