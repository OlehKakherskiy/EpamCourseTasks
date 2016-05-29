package being;

import beingState.State;
import beingState.humanState.StateTable;

/**
 * Class represents human being.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @version 1.0
 */
public class Human extends Being {

    /**
     * human name
     */
    private String name;

    /**
     * human surname
     */
    private String surname;

    public Human(String name, String surname, StateTable stateTable, State state) {
        super(stateTable, state);
        this.name = name;
        this.surname = surname;
        this.stateTable = stateTable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processCurrentState() {
        System.out.println(currentState.processState());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}