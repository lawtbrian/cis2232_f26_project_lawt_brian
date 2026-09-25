package ca.hccis.files;

import ca.hccis.files.entity.Vehicle;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Controller {

    // Location where the JSON file will be saved
    public static final String DIRECTORY = "C:\\cis2232";
    public static final String PATH_NAME =
            "C:\\cis2232\\data_lawt_brian.json";

    // Menu options
    public static final String MENU =
            "A) Add" + System.lineSeparator()
                    + "V) View" + System.lineSeparator()
                    + "X) eXit";

    // Stores all of the vehicles
    private static HashMap<Integer, Vehicle> vehicleMap = new HashMap<>();

    // Used to convert Vehicle objects to and from JSON
    private static Gson gson = new Gson();


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Make sure the cis2232 directory exists
        createDirectory();

        // Load any vehicles that were previously saved
        readAll();

        String option = "";

        // Keep showing the menu until the user chooses X
        while (!option.equalsIgnoreCase("X")) {

            System.out.println();
            System.out.println("AUTO TRACK");
            System.out.println("----------");
            System.out.println(MENU);
            System.out.print("Enter option: ");

            option = input.nextLine();

            if (option.equalsIgnoreCase("A")) {

                addVehicle();

            } else if (option.equalsIgnoreCase("V")) {

                viewAll();

            } else if (!option.equalsIgnoreCase("X")) {

                System.out.println("Invalid option.");
            }
        }

        System.out.println("Thank you for using Auto Track.");
    }


    /**
     * Create the cis2232 directory if it does not already exist.
     */
    public static void createDirectory() {

        File directory = new File(DIRECTORY);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }


    /**
     * Add a new vehicle.
     */
    public static void addVehicle() {

        // Automatically determine the next vehicle ID
        int vehicleId = getNextVehicleId();

        Vehicle vehicle = new Vehicle(vehicleId);

        // Ask the user for the vehicle information
        vehicle.getInformation();

        // Add the vehicle to the HashMap
        vehicleMap.put(vehicleId, vehicle);

        // Save the vehicles to the JSON file
        writeAll();

        System.out.println("Vehicle added successfully.");
    }


    /**
     * Determine the next available vehicle ID.
     */
    public static int getNextVehicleId() {

        int highestId = 0;

        for (Integer vehicleId : vehicleMap.keySet()) {

            if (vehicleId > highestId) {
                highestId = vehicleId;
            }
        }

        return highestId + 1;
    }


    /**
     * Display all vehicles.
     */
    public static void viewAll() {

        if (vehicleMap.isEmpty()) {

            System.out.println("No vehicles have been added.");

        } else {

            System.out.println();
            System.out.println("--- Vehicles ---");

            for (Vehicle currentVehicle : vehicleMap.values()) {

                System.out.println();
                System.out.println(currentVehicle);
                System.out.println("--------------------");
            }
        }
    }


    /**
     * Save all vehicles to the JSON file.
     */
    public static void writeAll() {

        try {

            FileWriter writer = new FileWriter(PATH_NAME, false);

            for (Vehicle currentVehicle : vehicleMap.values()) {

                // Convert the Vehicle object to JSON
                writer.append(gson.toJson(currentVehicle));

                writer.append(System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    /**
     * Read previously saved vehicles from the JSON file.
     */
    public static void readAll() {

        vehicleMap.clear();

        File file = new File(PATH_NAME);

        // If the file does not exist yet, there is nothing to load
        if (!file.exists()) {
            return;
        }

        try {

            List<String> lines =
                    Files.readAllLines(Paths.get(PATH_NAME));

            for (String line : lines) {

                // Convert the JSON back into a Vehicle object
                Vehicle vehicleFromJson =
                        gson.fromJson(line, Vehicle.class);

                // Put the vehicle back into the HashMap
                vehicleMap.put(
                        vehicleFromJson.getVehicleId(),
                        vehicleFromJson
                );
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}