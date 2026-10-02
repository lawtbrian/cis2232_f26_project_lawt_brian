package ca.hccis.files.bo;

import ca.hccis.files.entity.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleAITest {

    /**
     * Tests the calculation with standard vehicle costs.
     */
    @Test
    public void testStandardCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(12500);
        vehicle.setEstimatedRepairs(800);
        vehicle.setOtherCosts(500);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(13800, result, 0.01);
    }

    /**
     * Tests the calculation when estimated repairs are zero.
     */
    @Test
    public void testZeroRepairs() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(15000);
        vehicle.setEstimatedRepairs(0);
        vehicle.setOtherCosts(500);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(15500, result, 0.01);
    }

    /**
     * Tests the calculation when other costs are zero.
     */
    @Test
    public void testZeroOtherCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(10000);
        vehicle.setEstimatedRepairs(1000);
        vehicle.setOtherCosts(0);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(11000, result, 0.01);
    }

    /**
     * Tests the calculation when both additional costs are zero.
     */
    @Test
    public void testNoAdditionalCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(20000);
        vehicle.setEstimatedRepairs(0);
        vehicle.setOtherCosts(0);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(20000, result, 0.01);
    }

    /**
     * Tests the calculation using decimal monetary values.
     */
    @Test
    public void testDecimalCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(12500.50);
        vehicle.setEstimatedRepairs(800.25);
        vehicle.setOtherCosts(499.99);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(13800.74, result, 0.01);
    }

    /**
     * Tests the calculation when all costs are zero.
     */
    @Test
    public void testAllZeroCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(0);
        vehicle.setEstimatedRepairs(0);
        vehicle.setOtherCosts(0);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(0, result, 0.01);
    }

    /**
     * Tests the calculation using large vehicle costs.
     */
    @Test
    public void testLargeCosts() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(100000);
        vehicle.setEstimatedRepairs(15000);
        vehicle.setOtherCosts(5000);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(120000, result, 0.01);
    }

    /**
     * Tests that unrelated vehicle fields do not affect the calculation.
     */
    @Test
    public void testUnrelatedFieldsDoNotAffectCalculation() {
        Vehicle vehicle = new Vehicle();

        vehicle.setAskingPrice(12000);
        vehicle.setEstimatedRepairs(1000);
        vehicle.setOtherCosts(500);

        vehicle.setMarketValue(50000);
        vehicle.setMileage(200000);
        vehicle.setAccidentClaimAmount(10000);
        vehicle.setOwnershipCount(5);
        vehicle.setInspectionStatus(false);

        VehicleBO calculator = new VehicleBO();

        double result = calculator.calculate(vehicle);

        assertEquals(13500, result, 0.01);
    }
}