package roadTask;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import roadTask.vehicle.Car;
import roadTask.vehicle.Motorcycle;
import roadTask.vehicle.Truck;
import roadTask.vehicle.Vehicle;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CheckPointTest {

    static CheckPoint standardCheckpoint;

    static CheckPoint hasNoTrucksCheckpoint;

    static CheckPoint hasNoCrosses = new Crossroad("s", "s1");

    @BeforeClass
    public static void init() {
        standardCheckpoint = new Crossroad("st1", "st2");
        standardCheckpoint.addVehicles(
                new Car("CP 111-11 P", "DAEWOO", "Sense"),
                new Car("CP 111-10", "FIAT", "bla-bla"),
                new Truck("CO 101-15 A", "TruckBrand", "truckName"),
                new Truck("CO 101-16 B", "TruckBrand1", "truckName1"),
                new Truck("CO 101-17 C", "TruckBran2", "truckName2"),
                new Truck("CO 101-18 D", "TruckBrand3", "truckName3"),
                new Truck("CO 101-19 E", "TruckBrand4", "truckName4"),
                new Truck("CO 101-20 F", "TruckBrand5", "truckName5"),
                new Car("OK 111-11 G", "VOLVO", "bla-bla1"),
                new Motorcycle("OK 111-44 P", "Ducati", "sdsds"),
                new Motorcycle("СА 234-34 В", "YAMAHA", "name"));

        hasNoTrucksCheckpoint = new Crossroad("t", "s");
        hasNoTrucksCheckpoint.addVehicles(
                new Car("CP 111-11 P", "DAEWOO", "Sense"),
                new Car("CP 111-10", "FIAT", "bla-bla"),
                new Car("OK 111-11 G", "VOLVO", "bla-bla1"),
                new Motorcycle("OK 111-44 P", "Ducati", "sdsds"),
                new Motorcycle("СА 234-34 В", "YAMAHA", "name"));

        hasNoCrosses = new Crossroad("s", "b");
    }

    @Test
    public void testCountCrossedVehicles() throws Exception {
        Assert.assertEquals(11, standardCheckpoint.countCrossedVehicles());
        Assert.assertEquals(0, hasNoCrosses.countCrossedVehicles());
        Assert.assertEquals(5, hasNoTrucksCheckpoint.countCrossedVehicles());
    }

    @Test
    public void testCountCrossedVehicles1() throws Exception {
        Assert.assertEquals(3, standardCheckpoint.countCrossedVehicles(Car.class));
        Assert.assertEquals(6, standardCheckpoint.countCrossedVehicles(Truck.class));
        Assert.assertEquals(0, hasNoTrucksCheckpoint.countCrossedVehicles(Truck.class));
    }

    @Test
    public void testHasCrossedUsingBrandAndModel() throws Exception {
        Assert.assertEquals(true, standardCheckpoint.hasCrossed("TruckBrand1", "truckName1"));
        Assert.assertEquals(false, hasNoTrucksCheckpoint.hasCrossed("TruckBrand1", "truckName1"));
        Assert.assertEquals(false, hasNoCrosses.hasCrossed("TruckBrand1", "truckName1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasCrossedUsingBrandAndModel1() throws Exception {
        standardCheckpoint.hasCrossed("", "truckName1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasCrossedUsingBrandAndModel2() throws Exception {
        standardCheckpoint.hasCrossed("TruckBrand1", null);
    }

    @Test
    public void testHasCrossedUsingVehicleObject() throws Exception {
        Assert.assertEquals(true, standardCheckpoint.hasCrossed(new Truck("CO 101-17 C", "TruckBran2", "truckName2")));
        Assert.assertEquals(false, hasNoTrucksCheckpoint.hasCrossed(new Truck("CO 101-17 C", "TruckBran2", "truckName2")));
        Assert.assertEquals(false, hasNoCrosses.hasCrossed(new Truck("CO 101-17 C", "TruckBran2", "truckName2")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasCrossedUsingVehicleObject1() throws Exception {
        Vehicle v = null;
        standardCheckpoint.hasCrossed(v);
    }

    @Test
    public void testHasCrossedUsingPlateNumber() throws Exception {
        Assert.assertEquals(true, standardCheckpoint.hasCrossed("CO 101-17 C"));
        Assert.assertEquals(false, hasNoTrucksCheckpoint.hasCrossed("CO 101-17 C"));
        Assert.assertEquals(false, hasNoCrosses.hasCrossed("CO 101-17 C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasCrossedUsingPlateNumber1() throws Exception {
        String plateNumber = null;
        standardCheckpoint.hasCrossed(plateNumber);
    }

}