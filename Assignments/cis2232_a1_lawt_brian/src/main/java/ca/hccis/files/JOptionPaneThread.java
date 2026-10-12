
package ca.hccis.files;

import javax.swing.JOptionPane;
import ca.hccis.files.entity.Vehicle;
import ca.hccis.files.bo.VehicleBO;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.concurrent.CancellationException;

/**
 * Runs the Auto Track JOptionPane interface
 * on a separate thread.
 */
public class JOptionPaneThread implements Runnable {

    @Override
    public void run() {

        String option = "";

        while (!option.equalsIgnoreCase("X")) {

            option = JOptionPane.showInputDialog(
                    null,
                    "AUTO TRACK\n\n"
                            + "A) Add\n"
                            + "V) View\n"
                            + "X) eXit\n\n"
                            + "Enter option:"
            );

            if (option == null) {
                break;
            }

            if (option.equalsIgnoreCase("A")) {
                try {
                    Vehicle vehicle = new Vehicle();

                    String make = getInput("Enter make:");

                    if (make.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Vehicle make cannot be empty."
                        );
                        continue;
                    }

                    vehicle.setMake(make);

                    String model = getInput("Enter model:");

                    if (model.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Vehicle model cannot be empty."
                        );
                        continue;
                    }

                    vehicle.setModel(model);

                    int year = getNonNegativeInt("Enter year:");

                    if (year == 0) {
                        throw new IllegalArgumentException(
                                "Vehicle year must be greater than zero."
                        );
                    }

                    vehicle.setYear(year);


                    vehicle.setMileage(
                            getNonNegativeDouble("Enter mileage (km):"));

                    vehicle.setAskingPrice(
                            getNonNegativeDouble("Enter asking price:")
                    );

                    vehicle.setMarketValue(
                            getNonNegativeDouble("Enter market value:")
                    );

                    vehicle.setAccidentClaimAmount(
                            getNonNegativeDouble("Enter accident claim amount:")
                    );

                    vehicle.setEstimatedRepairs(
                            getNonNegativeDouble("Enter estimated repair costs:")
                    );

                    vehicle.setOwnershipCount(
                            getNonNegativeInt("Enter ownership count:"));


                    String inspectionInput = getInput(
                            "Has the vehicle passed inspection? (true/false):"
                    );

                    if (!inspectionInput.equalsIgnoreCase("true")
                            && !inspectionInput.equalsIgnoreCase("false")) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter true or false."
                        );

                        continue;
                    }

                    vehicle.setInspectionStatus(
                            Boolean.parseBoolean(inspectionInput)
                    );


                    vehicle.setOtherCosts(
                            getNonNegativeDouble("Enter other costs:"));

                    int vehicleId = Controller.saveVehicle(vehicle);


                    // Calculate the total estimated cost
                    VehicleBO vehicleBO = new VehicleBO();
                    double totalCost = vehicleBO.calculate(vehicle);

                    JOptionPane.showMessageDialog(
                            null,
                            "Vehicle added successfully!"
                                    + "\nVehicle ID: " + vehicleId
                                    + "\nTotal Estimated Cost: $"
                                    + String.format("%.2f", totalCost)
                                    + "\nTotal vehicles: " + Controller.getVehicleCount()
                    );

                } catch (CancellationException e) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Vehicle entry cancelled."
                    );
                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid number entered. Vehicle was not saved."
                    );
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            e.getMessage()
                    );
                } catch (IllegalStateException e) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error: Unable to save vehicle to the JSON file."
                    );
                }

            } else if (option.equalsIgnoreCase("V")) {
                // Get all vehicles from the shared HashMap
                String vehicleInfo = Controller.getAllVehicles();

                // Create a text area to display the vehicles
                JTextArea textArea = new JTextArea(vehicleInfo);

                // Prevent users from editing vehicle information
                textArea.setEditable(false);

                // Start displaying from the first vehicle
                textArea.setCaretPosition(0);

                // Add a scrollbar for longer vehicle lists
                JScrollPane scrollPane = new JScrollPane(textArea);

                // Set the size of the viewing area
                scrollPane.setPreferredSize(
                        new java.awt.Dimension(450, 400)
                );

                JOptionPane.showMessageDialog(
                        null,
                        scrollPane,
                        "Auto Track - Vehicles",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else if (!option.equalsIgnoreCase("X")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid option."
                );
            }
        }
    }
    /**
     * Get input from JOptionPane.
     *
     * @param message The message displayed to the user
     * @return The user's input
     */
    private String getInput(String message) {

        String input = JOptionPane.showInputDialog(message);

        if (input == null) {
            throw new CancellationException();
        }

        return input;
    }

    /**
     * Gets a non-negative number from the user.
     *
     * @param message The input prompt
     * @return A non-negative number
     */
    private double getNonNegativeDouble(String message) {

        double value = Double.parseDouble(getInput(message));

        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(
                    "Please enter a valid non-negative number."
            );
        }

        return value;
    }

    private int getNonNegativeInt(String message) {

        int value = Integer.parseInt(getInput(message));

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Please enter non-negative whole number."
            );
        }
        return value;
    }

}
