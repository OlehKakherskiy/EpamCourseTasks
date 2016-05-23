package roadTask;

import roadTask.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic realisation of {@link CheckPoint} interface. Don't have any specific state, except list of
 * vehicles, that have already passed this checkpoint.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class AbstractCheckPoint implements CheckPoint {

    /**
     * list, that stores all vehicles, that have passed this checkpoint
     */
    protected List<Vehicle> crossedVehicles;

    /**
     * exception message, that is thrown when specific vehicle is null
     */
    protected static final String nullVehicleExceptionText = "vehicle object mustn't be null";

    /**
     * exception message, that is thrown when specific brand name or model name, that is taken part
     * in searching vehicles, is empty or null
     */
    protected static final String brandOrModelInapplicableException = "brand name and model name mustn't be null or empty";

    /**
     * exception message, that is thrown when specific plate number, that is taken part
     * in searching vehicles, is empty or null
     */
    protected static final String plateNumberInapplicableException = "plate number mustn't be null or empty";

    /**
     * Creates new object with empty list of passed vehicles
     */
    public AbstractCheckPoint() {
        crossedVehicles = new ArrayList<>();
    }

    public AbstractCheckPoint(Vehicle... vehicles) {
        if (hasNullVehicles(vehicles))
            throw new IllegalArgumentException(nullVehicleExceptionText);

        crossedVehicles.addAll(Arrays.asList(vehicles));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int countCrossedVehicles() {
        return crossedVehicles.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int countCrossedVehicles(Class<? extends Vehicle> vehicleType) {
        int counter = 0;
        for (Vehicle vehicle : crossedVehicles) {
            if (vehicleType.isAssignableFrom(vehicle.getClass())) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCrossed(Vehicle vehicle) {
        if (vehicle == null)
            throw new IllegalArgumentException(nullVehicleExceptionText);
        for (Vehicle currentVehicle : crossedVehicles) {
            if (currentVehicle.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCrossed(String brand, String model) {
        if (isInapplicable(brand) || isInapplicable(model)) {
            throw new IllegalArgumentException(brandOrModelInapplicableException);
        }
        for (Vehicle currentVehicle : crossedVehicles) {
            if (currentVehicle.getBrand().equalsIgnoreCase(brand) &&
                    currentVehicle.getModel().equalsIgnoreCase(model)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCrossed(String plateNumber) {
        if (isInapplicable(plateNumber)) {
            throw new IllegalArgumentException(plateNumberInapplicableException);
        }
        for (Vehicle currentVehicle : crossedVehicles) {
            if (currentVehicle.getNumberPlate().equals(plateNumber))
                return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVehicles(Vehicle... vehicles) {
        if (!hasNullVehicles(vehicles)) {
            crossedVehicles.addAll(Arrays.asList(vehicles));
        }
    }

    /**
     * checks whether vararg has null values
     *
     * @param vehicles
     * @return true, if list doesn't consist empty values
     */
    private boolean hasNullVehicles(Vehicle... vehicles) {
        for (Vehicle v : vehicles) {
            if (v == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks whether specific string is empty or null
     *
     * @param name specific string
     * @return true if specific string is empty or null
     */
    private boolean isInapplicable(String name) {
        return name == null || name.equals("");
    }
}
