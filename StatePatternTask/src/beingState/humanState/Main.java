package beingState.humanState;

import being.Human;
import beingState.DefinedState;

import java.util.*;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        Set<String> fishermanStates = new HashSet<>();
        fishermanStates.addAll(Arrays.asList("lakeName", "fishingRod"));

        Set<String> mushroomer = new HashSet<>();
        mushroomer.add("forestName");

        Set<String> hunter = new HashSet<>();
        hunter.addAll(Arrays.asList("animalName", "gunName", "gunManufacturer"));

        Set<String> sittingAtHome = new HashSet<>();
        sittingAtHome.add("sittingAtHome");

        StateTable table = new StateTable();

        Map<Set<String>, Class<? extends DefinedState>> stateTransitions = new HashMap<>();
        stateTransitions.put(fishermanStates, FishermanState.class);
        stateTransitions.put(mushroomer, MushroomerState.class);
        stateTransitions.put(hunter, HunterState.class);
        stateTransitions.put(sittingAtHome, SittingAtHomeState.class);
        table.setStateTransitions(stateTransitions);

        Human Borya = new Human("Borya", "Moiseev", table, new SittingAtHomeState());

        Map<String, Object> compositeState = new HashMap<>();
        compositeState.put("lakeName", "Lake:)");
        compositeState.put("animalName", "bear");
        compositeState.put("gunName", "Mosina");
        compositeState.put("gunManufacturer", "IJ");
        Borya.changeState(compositeState);
        Borya.processCurrentState();
        compositeState.clear();

        //throws exception because of incompatible attributes with states
        compositeState.put("justKey", "justValue");
        Borya.changeState(compositeState);
        Borya.processCurrentState();
    }
}
