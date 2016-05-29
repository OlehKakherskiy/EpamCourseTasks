package beingState;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A part of decision of implementation superposition state. This class is a
 * state, but it aggregates 2 and more {@link DefinedState}. To avoid tree-structure
 * state it aggregates objects of marker interface {@link DefinedState}, otherwise
 * it potentially could aggregate other {@link CompositeState} objects. All operations
 * of {@link State} are implemented by calling identical methods of each state in
 * {@link #states} and gathering the result of operations, if need.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 * @see DefinedState
 */
public class CompositeState implements State {

    /**
     * parts of this state superposition
     */
    private Set<DefinedState> states;

    public CompositeState() {
        states = new HashSet<>();
    }

    public CompositeState(DefinedState... states) {
        this();
        this.states.addAll(Arrays.asList(states));
    }

    public CompositeState(Set<DefinedState> states) {
        this.states = states;
    }

    /**
     * adds a {@link DefinedState} object to this composite state
     *
     * @param state simple state
     */
    public void addState(DefinedState state) {
        states.add(state);
    }

    /**
     * calls {@link #processState()} of each simple state and aggregates the
     * result of methods invoking.
     *
     * @return aggregated method results of each state in {@link #states}
     */
    @Override
    public String processState() {
        StringBuilder resultBuilder = new StringBuilder();
        for (DefinedState state : states) {
            resultBuilder.append(state.processState()).append("\n");
        }
        return resultBuilder.deleteCharAt(resultBuilder.length() - 1).toString();
    }


    /**
     * inits every state in {@link #states}
     *
     * @param stateAttributes key-value pairs of special attributes,
     */
    @Override
    public void initState(Map<String, Object> stateAttributes) {
        for (DefinedState state : states) {
            state.initState(stateAttributes);
        }
    }
}