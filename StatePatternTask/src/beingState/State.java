package beingState;


import java.util.Map;

/**
 * Represents operations, that must be implemented by every class to be human state.
 * Classes of this type are used by {@link being.Human} to define it's state (being
 * a mushroomer, fisherman etc.). Firstly, to get working object of this type, it
 * must be configured by calling {@link #initState(Map)}. To get state defined result
 * the {@link #processState()} must be called.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see being.Human
 */
public interface State {

    /**
     * processes the result of human being in current state
     *
     * @return string representation of being in current state
     */
    public String processState();

    /**
     * configures the state with special state attributes.
     * Each implementation knows about state attributes, it should use, and
     * the objects' types, so they can be converted to target type
     *
     * @param stateAttributes key-value pairs of special attributes,
     *                        that state needs to have
     */
    public void initState(Map<String, Object> stateAttributes);

}