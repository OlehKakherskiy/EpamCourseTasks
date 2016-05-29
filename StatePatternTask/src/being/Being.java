package being;

import beingState.State;
import beingState.humanState.StateTable;

import java.util.Map;

/**
 * Class represents a being, that can have special states
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class Being {

    /**
     * table that represents all possible states of this being
     */
    protected StateTable stateTable;

    /**
     * current being's state
     */
    protected State currentState;

    public Being(StateTable stateTable, State state) {
        this.stateTable = stateTable;
        currentState = state;
    }

    /**
     * calls the {@link State#processState()} method to get result of being in
     * this state
     */
    public abstract void processCurrentState();

    /**
     * changes the state of being according to current attributes
     *
     * @param stateAttributes attributes of next state, if null or empty - do nothing
     */
    public void changeState(Map<String, Object> stateAttributes) {
        if (stateAttributes == null || stateAttributes.size() == 0)
            return;
        try {
            currentState = stateTable.changeState(stateAttributes);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
