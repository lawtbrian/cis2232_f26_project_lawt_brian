package ca.hccis.files.bo;

import ca.hccis.files.entity.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleTest {
    /**
     * Testing the total estimated cost using normal vehicle costs
     * Created following a test driven approach
     */
    @Test
    public void testCalculate() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(12500);
        vehicle.setEstimatedRepairs(800);
        vehicle.setOtherCosts(500);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);
        assertEquals(13800, result, 0.01);
    }

    /**
     * Tests the total estimated cost with no repair or other costs.
     * This test was created following a Test Driven Development approach.
     */
    @Test
    public void testCalculateNoExtraCosts(){
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(12500);
        vehicle.setEstimatedRepairs(0);
        vehicle.setOtherCosts(0);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(12500, result, 0.01);
    }
    /**
     * Tests that the calculated total includes all vehicle costs.
     * This test was created following a test driven approach.
     */
    @Test
    public void testCalculateAllCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(10000);
        vehicle.setEstimatedRepairs(1500);
        vehicle.setOtherCosts(750);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertTrue(result == 12250);
    }

}