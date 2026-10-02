package ca.hccis.files.bo;

import ca.hccis.files.entity.Vehicle;

/**
 * Business object used to perform calculations for Auto Track vehicles.
 *
 * @author Brian Lawt
 * @since 2026-10-01
 */
public class VehicleBO {

    /**
     * Calculates the total estimated cost of a vehicle.
     * The total includes the asking price, estimated repairs, and other costs.
     *
     * @param vehicle the vehicle containing the costs
     * @return the total estimated cost of the vehicle
     */
    public double calculate(Vehicle vehicle) {
        return vehicle.getAskingPrice()
                + vehicle.getEstimatedRepairs()
                + vehicle.getOtherCosts();
    }
}