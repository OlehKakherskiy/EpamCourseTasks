package roadTask;

import roadTask.vehicle.Vehicle;

/**
 * Represents every location object that can register {@link Vehicle} that passed it.
 * Also can submit whether a specific vehicle has passed this point using it {@link Vehicle#brand}
 * and {@link Vehicle#model} or {@link Vehicle#numberPlate}. Can count all vehicles, that passed this
 * point or vehicles of specific type.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see Vehicle
 */
public interface CheckPoint {

    /**
     * counts all vehicles that crossed this checkpoint
     *
     * @return >0 if at least 1 vehicle passed the checkpoint, otherwise - 0.
     */
    int countCrossedVehicles();

    /**
     * counts all vehicles of specific type that crossed this checkpoint
     *
     * @param vehicleType type of vehicles, that counts
     * @return >0 if at least 1 vehicle passed the checkpoint, otherwise - 0.
     */
    int countCrossedVehicles(Class<? extends Vehicle> vehicleType);

    /**
     * Check if specific vehicle is passed this checkpoint
     *
     * @param vehicle object representation of vehicle
     * @return true if specific vehicle has passed this checkpoint, otherwise - false.
     */
    boolean hasCrossed(Vehicle vehicle);

    /**
     * Check if a vehicle with the specific brand and model has passed this checkpoint.
     *
     * @param brand vehicle brand (should be {@link Vehicle#brand} field)
     * @param model vehicle model (should be {@link Vehicle#model} field)
     * @return true if vehicle with these brand and model has passed this checkpoint
     */
    boolean hasCrossed(String brand, String model);

    /**
     * Check if a vehicle with the specific plate number has passed this checkpoint.
     *
     * @param plateNumber vehicle plateNumber (should be {@link Vehicle#numberPlate} field)
     * @return true if vehicle with the specific plate number this checkpoint
     */
    boolean hasCrossed(String plateNumber);

    /**
     * adds all vehicles from this list to specific list of passed vehicles. Vehicle
     * shouldn't be null.
     *
     * @param vehicles vehicles, that has passed this checkpoint.
     */
    void addVehicles(Vehicle... vehicles);
}
