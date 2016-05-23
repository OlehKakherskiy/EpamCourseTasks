package roadTask.vehicle;

/**
 * Class specified a vehicle, that can cross checkpoints.
 * Every vehicle has special plateNumber, model and brand names.
 * Brand and model names are given to vehicle from creation, but
 * number plate can be changed.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class Vehicle {

    /**
     * vehicle's number plate
     */
    protected String numberPlate;

    /**
     * vehicle's model name
     */
    protected String model;

    /**
     * vehicle's brand name
     */
    protected String brand;

    /**
     * creates new vehicle, inits all fields
     *
     * @param numberPlate specific number plate
     * @param model       specific model name
     * @param brand       specific brand name
     */
    public Vehicle(String numberPlate, String model, String brand) {
        this.numberPlate = numberPlate;
        this.model = model;
        this.brand = brand;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        if (numberPlate == null || numberPlate.equals(""))
            return;
        this.numberPlate = numberPlate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * two vehicles are equals if they have equal plate numbers or
     * equal brand and model names (they compare ignoring the case)
     *
     * @param o another object
     * @return true, if objects are equal, otherwise - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;

        return numberPlate.equals(vehicle.numberPlate) && model.equalsIgnoreCase(vehicle.model)
                && brand.equalsIgnoreCase(vehicle.brand);

    }

    @Override
    public int hashCode() {
        int result = numberPlate.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + brand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{numberPlate='" + numberPlate + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
